package baobao.multithread.producerandconsumer;

/**
 * @author baobao
 * @date 2019/7/22 0022 17:01
 * @description  产品数，代表多线程共享数据
 */
public class Product {
    private int productNum = 0;

    public void increase(){
        synchronized (this){
            notifyAll();
            productNum++;
            System.out.println(Thread.currentThread().getName() + " 生产了1个产品," + "当前产品数为 " + productNum);
            if (productNum == 20){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void decrease(){
        synchronized (this){
            notifyAll();
            productNum--;
            System.out.println(Thread.currentThread().getName() + " 消费了1个产品," + "当前产品数为 " + productNum);
            if (productNum == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
