package baobao.java8.lambda;

/**
 * @author baobao
 * @create 2020-03-23 21:23
 * @description
 */
public class LambdaAndInnerClass {
    Runnable r1 = () -> System.out.println(Thread.currentThread().getName() + ":" + this);

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + this);
        }
    };


    public static void main(String[] args) {
        LambdaAndInnerClass lambdaAndInnerClass = new LambdaAndInnerClass();
        System.out.println(lambdaAndInnerClass);
        new Thread(lambdaAndInnerClass.r1,"r1").start();
        new Thread(lambdaAndInnerClass.r2,"r2").start();
    }
}
