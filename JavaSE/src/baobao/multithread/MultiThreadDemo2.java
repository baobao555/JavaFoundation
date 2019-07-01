package baobao.multithread;

public class MultiThreadDemo2 {

	public static void main(String[] args) {
		Ticket1 t = new Ticket1();
		Thread t1 =new Thread(t);
		Thread t2 =new Thread(t);
		Thread t3 =new Thread(t);
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.start();
		t2.start();
		t3.start();
	}

}

class Ticket1 implements Runnable{
	int ticket = 100;
	@Override
	/*public synchronized void run() {
		while(ticket > 0) {
			System.out.println(Thread.currentThread().getName() + "卖票:" + ticket--);
		}
	}*/
	
	public void run() {
		synchronized(this) {
			while(ticket > 0) {
					System.out.println(Thread.currentThread().getName() + "卖票:" + ticket--);
			}
		}
	}
}