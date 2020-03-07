package designmode.prototype;

import java.io.*;

/**
 * @author baobao
 * @create 2020-02-24 20:56
 * @description
 */
public class Sheep implements Cloneable, Serializable {
    private String name;
    private int age;
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Sheep() {
    }

    public Sheep(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Sheep(String name, int age, Person owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //克隆对象。类必须实现Cloneable接口，否则调用此方法会抛出异常
    //这种方式是浅拷贝
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //实现深拷贝的方式1：克隆对象后再将对象的引用类型单独拿出来调用其clone方法
    public Sheep myDeepClone() throws CloneNotSupportedException {
        //克隆Sheep
        Sheep sheep = (Sheep) super.clone();
        //将Sheep的引用类型属性owner再克隆一遍
        sheep.owner = (Person) sheep.owner.clone();
        return sheep;
    }

    //实现深拷贝的方式2：序列化。源类型Sheep和其引用类型属性Person必须同时实现Serializable接口
    public Sheep deepCloneSerilize() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        //将对象写入到ByteArrayOutputStream
        oos.writeObject(this);
        //读取ByteArrayOutputStream的字节数据
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //将ByteArrayInputStream中的数据还原为对象
        ObjectInputStream ois = new ObjectInputStream(bais);
        Sheep sheep = (Sheep) ois.readObject();
        oos.close();
        ois.close();
        return sheep;
    }




    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", owner=" + owner +
                '}';
    }


}
