package baobao.iostream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOExceptionHandleDemo  {
	public static void main(String[] args) throws IOException {
		try (
			FileInputStream fis = new FileInputStream("xxx.txt");
			FileOutputStream fos = new FileOutputStream("yyy.txt");//jdk7新方式，继承了autocloseable接口的可以自动关闭流
			)
		{
		int b;
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
		}
	
	}

	private static void jdk6() throws FileNotFoundException, IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("xxx.txt");
			fos = new FileOutputStream("yyy.txt");
		int b;
		while((b = fis.read()) != -1) {
			fos.write(b);
		}
	}finally {
		try {
		if (fis != null)
			fis.close();
		}finally {
		if (fos != null)
			fos.close();
		}
	}
	}
}
