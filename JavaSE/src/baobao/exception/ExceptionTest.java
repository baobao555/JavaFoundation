package baobao.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionTest {
	public static void main(String[] args){
		try {
			int i = 2/0;
			FileInputStream fis  = new FileInputStream("1.txt");
		}catch(ArithmeticException e) {
			System.out.println("抓住了算数异常");
		}
		catch (FileNotFoundException e) {
			System.out.println("抓住了文件找不到异常");//多个catch注意顺序，大的异常不能放在小的前面，否则小的异常分支会unreachable
		}
	}
}
