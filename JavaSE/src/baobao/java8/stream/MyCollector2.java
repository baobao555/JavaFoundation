package baobao.java8.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author baobao
 * @create 2020-03-24 20:58
 * @description
 */
public class MyCollector2<T> implements Collector<T, Set<T>,Map<T,T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        return () -> {
            //观察创建了多少个容器
            System.out.println("supplier:创建了一个容器");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return (set,item) -> {
            //观察是否多线程调用，是否有多个可变容器
            System.out.println("accumulator:" + Thread.currentThread().getName() + set);
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return (s1,s2) -> {
            //观察是否有调用将中间结果合并
            System.out.println("combiner:" + s1 + s2);
            s1.addAll(s2);
            return s1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        return set -> {
            //观察是否有调用finish
            System.out.println("finisher:Set to Map");
            HashMap<T, T> map = new HashMap<>();
            set.forEach(s -> map.put(s, s));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED,Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("curry", "thompson", "baobao", "k", "l", "a", "c", "d", "b", "s");
        HashSet<String> set = new HashSet<>();
        set.addAll(list);
        System.out.println(set);

        Map<String, String> map = set.parallelStream().collect(new MyCollector2<>());
        System.out.println(map);
    }
}
