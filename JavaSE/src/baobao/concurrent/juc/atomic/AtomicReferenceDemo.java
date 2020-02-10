package baobao.concurrent.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author baobao
 * @create 2019-09-15 0:21
 * @description 演示AtomicReference
 */

/*
* 除了基本数据类型以外，可以用AtomicReference<V>保证任意V类型的原子性
* */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User u1 = new User("baobao1", 18);
        User u2 = new User("baobao2", 28);

        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1, u2) + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1, u2) + atomicReference.get().toString());
    }
}

class User{
    String userName;
    int age;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}