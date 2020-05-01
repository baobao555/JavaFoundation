package baobao.jvm.classloader.myclassloader;

/**
 * @author baobao
 * @create 2020-04-05 20:58
 * @description
 */
public class Animal {
    public Animal(){
        System.out.println("Animal is loaded by:" + this.getClass().getClassLoader());
        //主动使用Cat
        new Cat();
        System.out.println("from Animal:" + Cat.class);
    }
}
