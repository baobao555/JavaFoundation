package baobao.jvm.gc;

/**
 * @author baobao
 * @create 2020-04-28 22:03
 * @description 演示G1日志
 */
public class GCTest6 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[size];
        System.out.println("myAlloc1创建对象");
        byte[] myAlloc2 = new byte[size];
        System.out.println("myAlloc2创建对象");
        byte[] myAlloc3 = new byte[size];
        System.out.println("myAlloc3创建对象");
        byte[] myAlloc4 = new byte[size];
        System.out.println("myAlloc4创建对象");
    }
}
