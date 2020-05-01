package baobao.exception;

import java.io.FileInputStream;

/**
 * @author baobao
 * @create 2020-03-18 20:49
 * @description
 */
public class CompileExceptionDemo {
    public static void main(String[] args) {
        try {
            readFile("a.txt");
        }catch (Exception e){
            System.out.println("捕获异常");
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }

    private static void readFile(String fileName) throws Exception{
        FileInputStream fis = new FileInputStream(fileName);
    }
}
