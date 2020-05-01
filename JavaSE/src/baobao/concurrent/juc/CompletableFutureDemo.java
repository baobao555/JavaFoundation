package baobao.concurrent.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author baobao
 * @create 2020-03-29 20:46
 * @description
 *
 * CompletableFuture实现了Future接口，比Future拥有更强大的功能：
 * 1、Future调用get方法获取结果时，如果任务没有执行完成，会阻塞线程。而CompletableFuture可以注册回调函数
 * 2、CompletableFuture可以支持级联调用
 *
 * 注意：
 * CompletableFuture内部维护了一个线程池，里面所有线程都是守护线程。
 * 所以在main方法中测试时注意手动将main方法阻塞，否则main方法一结束，
 * CompletableFuture的任务还没来得及执行就会因为main的结束和随之结束
 *
 * CompletableFuture常用方法：
 * 1、静态方法创建异步任务
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 *
 * runnable的异步任务没有返回值，Supplier的异步任务支持返回值
 * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。
 * 如果指定线程池，则使用指定的线程池运行
 *
 * 2、异步任务执行完成时的回调方法
 * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action，
 * Action的类型是BiConsumer<? super T,? super Throwable>它可以处理正常的计算结果，或者异常情况
 *
 * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
 *
 * whenComplete 和 whenCompleteAsync 的区别：
 * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
 * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行
 *
 * 3、thenApply
 * 当一个线程依赖另一个线程的执行结果时，可以使用thenApply方法来把这两个线程串行化
 * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 *
 * Function<? super T,? extends U>
 * T：上一个任务返回结果的类型
 * U：当前任务的返回值类型
 *
 * 4、handle
 * handle是执行任务完成时对结果的处理。handle方法和thenApply方法处理方式基本一样，不同的是handle是在任务完成后
 * 再执行，还可以处理异常的任务。thenApply只可以执行正常的任务任务出现异常则不执行thenApply方法。
 * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
 *
 * 5、thenAccept
 * 接收任务的处理结果，并消费处理，无返回结果
 * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
 *
 * 6、thenRun
 * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给thenRun方法，
 * 只是处理完任务后，执行thenRun的后续操作
 * public CompletionStage<Void> thenRun(Runnable action);
 * public CompletionStage<Void> thenRunAsync(Runnable action);
 * public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
 *
 * 7、thenCombine合并任务
 * thenCombine会把两个CompletableFuture的任务都执行完成后，把两个任务的结果一块交给thenCombine来处理
 * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
 *
 * 8、thenAcceptBoth
 * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
 * public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action,     Executor executor);
 *
 * 9、applyToEither
 * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
 * public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);
 *
 * 10、acceptEither
 * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作
 * public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
 * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action);
 * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor);
 *
 * 11、runAfterEither
 * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
 * public CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor);
 *
 * 12、runAfterBoth
 * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
 * public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);
 *
 * 13、thenCompose
 * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
 * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
 * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) ;
 * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) ;
 *
 *
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        //异步执行任务并注册回调
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).whenComplete((r,e) -> System.out.println("supplyAsync:" + r));

        //将2个任务串行化，第二个任务接收第一个任务的执行结果
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).thenApply(i -> i + 5).whenComplete((r,e) -> System.out.println("thenApply:" + r));

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = 1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5 + 5;
        }).handle((i,t) -> {
            if (t != null){
                System.out.println(i);
                System.out.println(t);
                return 0;
            }else {
                return i + 15;
            }
        }).whenComplete((r,t) -> {
            if (t != null){
                System.out.println(t);
            }
            System.out.println("handle:" + r);
        });

        //后一个任务消费前一个任务的结果
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).thenAccept(System.out::println).whenComplete((r,e) -> System.out.println("thenAccept:" + r));



        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).thenRun(() -> System.out.println("baobao")).whenComplete((r,e) -> System.out.println("thenRun:" + r));


        //合并任务
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).thenCombine(CompletableFuture.supplyAsync(() -> 25),(r1,r2) -> r1 + r2)
                .whenComplete((r,e) -> System.out.println("thenCombine:" + r));

        //2个任务都执行完毕后，将2个结果一并消费
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> 25),(r1,r2) -> System.out.println(r2 * r1))
                .whenComplete((r,e) -> System.out.println("thenCombine:" + r));

        //两个任务谁执行返回的结果快，我就用那个任务的结果进行下一步的转化操作
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).applyToEither(CompletableFuture.supplyAsync(() -> 25),r -> r + 100)
                .whenComplete((r,e) -> System.out.println("applyToEither:" + r));

        //对两个任务进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 5 + 5;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 1000))
                .whenComplete((r,e) -> System.out.println("thenCompose:" + r));

        Thread.currentThread().join();
    }
}
