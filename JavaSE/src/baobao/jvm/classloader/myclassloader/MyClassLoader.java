package baobao.jvm.classloader.myclassloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author baobao
 * @create 2020-04-04 21:43
 * @description 自定义类加载器，从某个路径加载.class文件生成Class对象
 */
public class MyClassLoader extends ClassLoader {
    //自定义类加载器的名字
    private String name;
    //存放.class文件的路径
    private String path;

    //以应用类加载器为父加载器
    public MyClassLoader(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    //以传入的parent为父加载器
    public MyClassLoader(ClassLoader parent, String name, String path) {
        super(parent);
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Class<?> findClass(String name)  {
        System.out.println(getName() + "----->MyClassLoader.findClass():" + name);
        //将类的二进制名称中的.替换成路径符号\\，并和路径组合形成类的.class文件的完整路径
        String realPath = path + name.replace(".", "\\") + ".class";
        try (FileInputStream fis = new FileInputStream(realPath);
             FileChannel fileChannel = fis.getChannel()){
            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            //读取.class文件的二进制数据
            fileChannel.read(buffer);
            //调用defineClass将二进制数据转换成Class对象
            return defineClass(name, buffer.array(), 0, buffer.capacity());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
