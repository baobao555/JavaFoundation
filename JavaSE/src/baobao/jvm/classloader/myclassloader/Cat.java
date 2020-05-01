package baobao.jvm.classloader.myclassloader;

/**
 * @author baobao
 * @create 2020-04-05 20:58
 * @description
 */
public class Cat {
    public Cat() {
        System.out.println("Cat is loaded by:" + this.getClass().getClassLoader());
        //输出Animal.class
        //System.out.println("from Cat:" + Animal.class);
    }
}
