package baobao.jvm.classloader;


import javax.sound.midi.Soundbank;

/**
 * @author baobao
 * @create 2020-03-31 22:39
 * @description
 */
public class ClassLoaderTest6 {
    public static void main(String[] args) {
        Singleton.getInstance();
        System.out.println("main:" + Singleton.counter1);
        System.out.println("main:" + Singleton.counter2);
    }
}

class Singleton{
    public static int counter1 = 1;

    private static Singleton instance = new Singleton();

    private Singleton(){
        counter1++;
        counter2++; //准备阶段的意义
        System.out.println("Singleton Constructor:" + counter1);
        System.out.println("Singleton Constructor:" + counter2);
    }

    public static int counter2 = 0;

    public static Singleton getInstance(){
        return instance;
    }
}