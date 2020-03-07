package designmode.prototype;

/**
 * @author baobao
 * @create 2020-02-24 20:57
 * @description 演示原型设计模式
 *
 * 原型设计模式采用克隆的方式，使创建对象无需一个个new，简化对象的创建
 */
public class Client {
    public static void main(String[] args) throws Exception {
        //simpleCopy();
        //myDeepClone();
        deepCloneSerilize();
    }


    //传统方式创建多个相同的对象，只能靠new
    private static void traditionalCopy(){
        Sheep sheep1 = new Sheep("baobao", 18);
        Sheep sheep2 = new Sheep("baobao", 18);
        Sheep sheep3 = new Sheep("baobao", 18);
        Sheep sheep4 = new Sheep("baobao", 18);
        Sheep sheep5 = new Sheep("baobao", 18);
        Sheep sheep6 = new Sheep("baobao", 18);
    }

    //浅拷贝，只能拷贝基本类型和String；对于引用类型，只会将指针赋值给克隆的对象，不会重新创建对象
    private static void simpleCopy() throws CloneNotSupportedException {
        Sheep sheep1 = new Sheep("baobao", 18,new Person("baobao2", 28));
        Sheep sheep2 = (Sheep) sheep1.clone();
        System.out.println(sheep2);
        System.out.println(sheep1.getOwner() == sheep2.getOwner());
    }

    //深拷贝方式1：克隆对象后再将对象的引用类型单独拿出来调用其clone方法
    private static void myDeepClone() throws CloneNotSupportedException {
        Sheep sheep1 = new Sheep("baobao", 18,new Person("baobao2", 28));
        Sheep sheep2 = sheep1.myDeepClone();
        System.out.println(sheep2);
        System.out.println(sheep1.getOwner() == sheep2.getOwner());
    }

    //深拷贝方式2：序列化。必须实现Serializable接口
    private static void deepCloneSerilize() throws Exception {
        Sheep sheep1 = new Sheep("baobao", 18,new Person("baobao2", 28));
        Sheep sheep2 = sheep1.deepCloneSerilize();
        System.out.println(sheep2);
        System.out.println(sheep1.getOwner() == sheep2.getOwner());
    }
}
