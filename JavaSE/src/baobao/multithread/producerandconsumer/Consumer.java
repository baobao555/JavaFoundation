package baobao.multithread.producerandconsumer;

/**
 * @author baobao
 * @date 2019/7/22 0022 17:01
 * @description 消费者
 */
public class Consumer implements Runnable{
    private Product product;

    public Consumer(Product product){
        this.product = product;
    }
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            product.decrease();
        }
    }
}
