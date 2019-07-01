package baobao.iostream;

import java.io.*;


public class IOStreamDemo {
	public void test() {
		long start = System.currentTimeMillis();
		//copyFile1("G:\\电视剧\\01.mp4", "H:\\02.mp4");//1362ms
		//copyFile2("G:\\电视剧\\01.mp4", "H:\\02.mp4");//333ms
		//copyText1("C:\\Users\\admin\\Desktop\\1.txt", "C:\\Users\\admin\\Desktop\\2.txt");//11ms
		//copyText2("C:\\Users\\admin\\Desktop\\1.txt", "C:\\Users\\admin\\Desktop\\2.txt");//11ms
		//readUTF8("C:\\Users\\admin\\Desktop\\1utf8.txt");
		writeUTF8("C:\\Users\\admin\\Desktop\\2utf8.txt");
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	
	public void copyFile1(String src,String dest) { //字节流拷贝文件
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int len;
			while((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(fis != null)
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void copyFile2(String src,String dest) { //带缓冲的字节流拷贝文件
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(src)) ;
			bos = new BufferedOutputStream(new FileOutputStream(dest)) ;
			byte[] buf = new byte[1024];
			int len;
			while((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bos != null)
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(bis != null)
						bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void copyText1(String src,String dest) { //字符流拷贝文本文件
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(src);
			fw = new FileWriter(dest);
			char[] buf = new char[1024];
			int len;
			while((len = fr.read(buf)) != -1) {
				System.out.println(new String(buf));
				fw.write(buf, 0, len);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(fr != null)
						fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void copyText2(String src,String dest) {  //带缓冲的字符流拷贝文本文件
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(src));
			bw = new BufferedWriter(new FileWriter(dest));
			//char[] buf = new char[1024];
			//int len;
			String line;
			while((line = br.readLine()) != null) { //readLine方法可以读取一整行
				System.out.println(line);
				bw.write(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(br != null)
						br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void readUTF8(String src) {  //转换流读取UTF-8编码的文本
		BufferedReader br = null;
		InputStreamReader isr = null;
		try {
			br = new BufferedReader(new FileReader(src));
			isr = new InputStreamReader(new FileInputStream(src), "UTF-8");
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);//锘挎垜鏄竴涓猚oder
			}
			
			char[] buf = new char[1024];
			int len;
			while((len = isr.read(buf)) != -1) {
				System.out.println(new String(buf));//?我是一个coder
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(isr != null)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(br != null)
						br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void writeUTF8(String dest) {  //转换流写到UTF-8编码的文件
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(dest), "UTF-8");
			osw.write("我是一个coder");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(osw != null)
					osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
