package baobao.multithread.producerandconsumer;

/**
 * @author baobao
 * @date 2019/7/22 0022 17:00
 * @description 生产者
 */
public class Producer implements Runnable{
    private Product product;

    public Producer(Product product){
        this.product = product;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            product.increase();
        }
    }
}
