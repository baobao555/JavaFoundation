package baobao.concurrent.threadBaseApi;


/**
 * @author baobao
 * @create 2020-01-17 15:46
 * @description 自定义读写锁
 */
public class MyReadWriteLockDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        //开启10000个读线程
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                shareData.read();
            }, "readThread" + i).start();
        }

        //开启10个写线程
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                shareData.write(temp);
            }, "writeThread" + i).start();
        }

    }
}


class MyReadWriteLock{
    //当前正在读的线程数
    private int curReadThreads;
    //当前正在等待读的线程数
    private int curReadWaitingThreads;
    //当前正在写的线程数
    private int curWriteThreads;
    //当前正在等待写的线程数
    private int curWriteWaitingThreads;
    //是否写优先于读
    private boolean isWritePrefered;

    public MyReadWriteLock(){
        this(true);
    }

    public MyReadWriteLock(boolean isWritePrefered){
        this.isWritePrefered = isWritePrefered;
    }

    //获取读锁
    public synchronized void readLock() throws InterruptedException {
        //等待读线程数+1
        curReadWaitingThreads++;
        //如果有线程正在写，或写操作优先于读并且当前正在等待写的线程数大于0时，该读线程阻塞
        while (curWriteThreads > 0 || (isWritePrefered && (curWriteWaitingThreads > 0))){
            this.wait();
        }
        //获取到了读锁，正在读线程数+1
        curReadThreads++;
        //获取到了读锁，等待读线程数-1
        curReadWaitingThreads--;
    }

    //释放读锁
    public synchronized void readUnlock(){
        //正在读线程数-1
        curReadThreads--;
        //唤醒其他线程
        this.notifyAll();
    }

    //获取写锁
    public synchronized void writeLock() throws InterruptedException {
        //等待写线程数+1
        curWriteWaitingThreads++;
        //如果有线程正在读或者正在写，当前写线程阻塞
        while (curReadThreads > 0 || curWriteThreads > 0){
            this.wait();
        }
        //获取到了写锁，正在写线程数+1
        curWriteThreads++;
        //获取到了写锁，等待写线程数-1
        curWriteWaitingThreads--;
    }

    //释放写锁
    public synchronized void writeUnlock(){
        //正在写线程数-1
        curWriteThreads--;
        //唤醒其他线程
        this.notifyAll();
    }
}

//共享数据类
class ShareData{
    //数据
    private int data = 5;
    //读写锁
    private MyReadWriteLock readWriteLock = new MyReadWriteLock();

    //读数据
    public void read(){
        try {
            //获取读锁
            readWriteLock.readLock();
            //读取数据
            System.out.println(Thread.currentThread().getName() + " read:" + this.data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放读锁
            readWriteLock.readUnlock();
        }
    }

    //写数据
    public void write(int value){
        try {
            //获取写锁
            readWriteLock.writeLock();
            //写入数据
            this.data = value;
            System.out.println(Thread.currentThread().getName() + " write:" + this.data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            readWriteLock.writeUnlock();
        }
    }
}
