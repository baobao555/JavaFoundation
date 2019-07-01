package baobao.multithread;

public class MultiThreadDemo {

	public static void main(String[] args) {
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.start();
		t2.start();
		t3.start();
	}

}

class Ticket extends Thread{
	static int ticket = 100;
	@Override
	public void run() {
		synchronized (Ticket.class) {
			while(ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "卖票:" + ticket--);
			}
		
		}
	}
}
