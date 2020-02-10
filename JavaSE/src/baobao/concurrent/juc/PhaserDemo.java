package baobao.concurrent.juc;


import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-02-07 20:58
 * @description 演示Phaser
 *
 * Phaser介绍：
 * jdk7引入，翻译为阶段器。一个可重用的同步barrier。他类似于CountDownLatch和CyclicBarrier，
 * 但是Phaser更加灵活，而且侧重于“重用”.
 * 在Phaser内有2个重要状态，分别是phase和party:
 * 1.party:就是需要同步的线程，party=3就意味着Phaser对象当前管理着3个线程。类似于CountDownLatch和CyclicBarrier
 * 构造函数中需要传入的数值。但是Phaser更加灵活，可以不在构造时指定死这个数值，而是可以在运行时调用register()或
 * arriveAndDeregister()方法动态增加或减少这个数值
 * 2.phase:表示阶段，初值为0，当所有的线程执行完本轮任务，同时开始下一轮任务时，
 * 意味着当前阶段已结束，进入到下一阶段，phase的值自动加1，同时会调用boolean onAdvance(int phase, int registeredParties)方法
 *
 * boolean onAdvance(int phase, int registeredParties)方法的作用:
 * 1、当每一个阶段执行完毕，此方法会被自动调用，因此，重载此方法写入的代码会在每个阶段执行完毕时执行，
 * 相当于CyclicBarrier的barrierAction。
 * 2、当此方法返回true时，意味着Phaser被终止，因此可以巧妙的设置此方法的返回值来终止所有线程。
 *
 * Phaser工作机制：
 * 只有收到管理的party个线程中，每个线程都执行了phaser.arriveAndAwaitAdvance()，才会进入下一个阶段，否则阻塞等待。
 *
 * 方法解析：
 *     1、Phaser()：构造函数，创建一个Phaser；默认parties个数为0。此后我们可以通过register()、bulkRegister()方法来注册新的parties。
 *
 *     2、Phaser(int parties)：构造函数，初始一定数量的parties；相当于直接regsiter此数量的parties。
 *
 *     3、arrive()：到达，阻塞，等到当前phase下其他parties到达。如果没有register（即已register数量为0），调用此方法将会抛出异常，此方法返回当前phase周期数，如果Phaser已经终止，则返回负数。
 *
 *     4、arriveAndDeregister()：到达，并注销一个parties数量，非阻塞方法。注销，将会导致Phaser内部的parties个数减一（只影响当前phase），即下一个phase需要等待arrive的parties数量将减一。异常机制和返回值，与arrive方法一致。
 *
 *     5、arriveAndAwaitAdvance()：到达，且阻塞直到其他parties都到达，且advance。此方法等同于awaitAdvance(arrive())。如果你希望阻塞机制支持timeout、interrupted响应，可以使用类似的其他方法（参见下文）。如果你希望到达后且注销，而且阻塞等到当前phase下其他的parties到达，可以使用awaitAdvance(arriveAndDeregister())方法组合。此方法的异常机制和返回值同arrive()。
 *
 *     6、awaitAdvance(int phase)：阻塞方法，等待phase周期数下其他所有的parties都到达。如果指定的phase与Phaser当前的phase不一致，则立即返回。
 *
 *     7、awaitAdvanceInterruptibly(int phase)：阻塞方法，同awaitAdvance，只是支持interrupted响应，即waiter线程如果被外部中断，则此方法立即返回，并抛出InterrutedException。
 *
 *     8、awaitAdvanceInterruptibly(int phase,long timeout,TimeUnit unit)：阻塞方法，同awaitAdvance，支持timeout类型的interrupted响应，即当前线程阻塞等待约定的时长，超时后以TimeoutException异常方式返回。
 *
 *     9、forceTermination()：强制终止，此后Phaser对象将不可用，即register等将不再有效。此方法将会导致Queue中所有的waiter线程被唤醒。
 *
 *     10、register()：新注册一个party，导致Phaser内部registerPaties数量加1；如果此时onAdvance方法正在执行，此方法将会等待它执行完毕后才会返回。此方法返回当前的phase周期数，如果Phaser已经中断，将会返回负数。
 *
 *     11、bulkRegister(int parties)：批量注册多个parties数组，规则同10、。
 *
 *     12、getArrivedParties()：获取已经到达的parties个数。
 *
 *     13、getPhase()：获取当前phase周期数。如果Phaser已经中断，则返回负值。
 *
 *     14、getRegisteredParties()：获取已经注册的parties个数。
 *
 *     15、getUnarrivedParties()：获取尚未到达的parties个数。
 *
 *     16、onAdvance(int phase,int registeredParties)：这个方法比较特殊，表示当进入下一个phase时可以进行的事件处理，如果返回true表示此Phaser应该终止（此后将会把Phaser的状态为termination，即isTermination()将返回true。），否则可以继续进行。phase参数表示当前周期数，registeredParties表示当前已经注册的parties个数。
 */

/*
* 使用场景：
* 5个学生一起参加考试，一共有三道题，要求所有学生到齐才能开始考试，全部同学都做完第一题，
* 学生才能继续做第二题，全部学生做完了第二题，才能做第三题，所有学生都做完的第三题，考试才结束。
* 分析这个题目：这是一个多线程（5个学生）分阶段问题（考试考试、第一题做完、第二题做完、第三题做完），所以很适合用Phaser解决这个问题。
* */
public class PhaserDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建Phaser
        MyPhaser phaser = new MyPhaser();
        //创建线程数组，保存5个学生考试线程
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            //创建学生考试线程
            StudentExam studentExam = new StudentExam(phaser);
            threads[i] = new Thread(studentExam, "学生" + (i + 1));
            //每创建1个线程，给phaser动态注册1个party
            phaser.register();
        }
        //启动所有学生考试线程
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }

        //等待最后一个做第三题的阶段到来
        while (phaser.getPhase() != 3){
            TimeUnit.SECONDS.sleep(1);
        }
        //阻塞，等待最后一个阶段所有学生都做完第三题后继续
        phaser.awaitAdvance(3);
        //宣布考试结束
        System.out.println("考试结束");
    }
}

//模拟1个学生
class StudentExam implements Runnable{
    private Phaser phaser;

    public StudentExam(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        //第一阶段：去考场
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "到达考场");
        //到达考场后阻塞，等待所有其他学生都到达考场后再继续
        phaser.arriveAndAwaitAdvance();

        //第二阶段：做第一题
        System.out.println(threadName + "开始做第一题");
        //随机休眠一段时间
        sleepRandom();
        //做完第一题
        System.out.println(threadName + "做完了第一题");
        //做完第一题后阻塞，等待所有其他学生都做完后再继续
        phaser.arriveAndAwaitAdvance();

        //第三阶段：做第二题
        System.out.println(threadName + "开始做第二题");
        //随机休眠一段时间
        sleepRandom();
        //做完第二题
        System.out.println(threadName + "做完了第二题");
        //做第二题时学生3突然因身体不适退出考试
        if ("学生3".equals(threadName)){
            System.out.println(threadName + "身体不适，退出考试");
            //参加考试的学生数(即参与同步的线程数)减1
            phaser.arriveAndDeregister();
            return;
        }else {
            //其他学生做完第二题后阻塞，等待所有其他学生都做完后再继续
            phaser.arriveAndAwaitAdvance();
        }

        //第四阶段：做第三题
        System.out.println(threadName + "开始做第三题");
        sleepRandom();
        System.out.println(threadName + "做完了第三题");
        //做完第三题后阻塞，等待所有其他学生都做完后再继续
        phaser.arriveAndAwaitAdvance();
    }

    private void sleepRandom(){
        long duration = (long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyPhaser extends Phaser{
    /**
     * 当每个阶段执行结束时会调用
     * @param phase 上一个阶段值，第一次为0
     * @param registeredParties 当前阶段的线程数
     * @return true表示phaser终止，false表示phaser继续运行
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            //第一阶段：学生到达教室
            case 0:
                System.out.println("学生已经到齐，人数：" + getRegisteredParties());
                return false;
            //第二阶段：学生做第一题
            case 1:
                System.out.println("所有学生已经做完第一题，人数：" + getRegisteredParties());
                return false;
            //第一阶段：学生做第二题
            case 2:
                System.out.println("所有学生已经做完第二题，人数：" + getRegisteredParties());
                return false;
            //第一阶段：学生做第三题，做完考试结束
            case 3:
                System.out.println("所有学生已经做完第三题，人数：" + getRegisteredParties() + "考试结束");
                return false;
            default:
                return true;
        }
    }
}
