package baobao.jvm.bytecode;

/**
 * @author baobao
 * @create 2020-04-14 21:38
 * @description
 */
public class ByteCodeTest5 {
    public void test(Grandpa grandpa){
        System.out.println("grandpa");
    }

    public void test(Father father){
        System.out.println("father");
    }

    public void test(Son son){
        System.out.println("son");
    }

    public static void main(String[] args) {
        ByteCodeTest5 byteCodeTest5 = new ByteCodeTest5();
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();
        byteCodeTest5.test(g1);
        byteCodeTest5.test(g2);
    }
}

class Grandpa{

}

class Father extends Grandpa{

}

class Son extends Father{

}
