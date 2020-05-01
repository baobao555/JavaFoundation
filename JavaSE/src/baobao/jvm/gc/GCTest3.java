package baobao.jvm.gc;

/**
 * @author baobao
 * @create 2020-04-25 20:51
 * @description
 */
public class GCTest3 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[2 * size];
        System.out.println("11111");
        byte[] myAlloc2 = new byte[2 * size];
        System.out.println("22222");
        byte[] myAlloc3 = new byte[2 * size];
        System.out.println("33333");
        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("44444");
    }
}
