package baobao.jvm.gc;

/**
 * @author baobao
 * @create 2020-04-25 20:07
 * @description
 */
public class GCTest2 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[3 * size];
        System.out.println("11111");
        /*byte[] myAlloc2 = new byte[2 * size];
        System.out.println("22222");
        byte[] myAlloc3 = new byte[3 * size];
        System.out.println("33333");*/
    }
}
