package baobao.exception;

/**
 * @author baobao
 * @create 2020-03-18 20:49
 * @description
 */
public class RuntimeExceptionDemo {
    public static void main(String[] args) {
        try {
            divide(5, 0);
        }catch (Exception e){
            System.out.println("捕获异常");
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }

    private static int divide(int a,int b){
        return a / b;
    }
}
