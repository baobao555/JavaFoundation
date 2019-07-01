package baobao.designmode;

public class TemplateDemo {

	public static void main(String[] args) {
		

	}

}

abstract class Template{
	public abstract void code(); //模板设计模式，计算一段代码的执行时间
	
	public void costTime() {
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}

class Demo extends Template {

	@Override
	public void code() {
		
	}
	
}
