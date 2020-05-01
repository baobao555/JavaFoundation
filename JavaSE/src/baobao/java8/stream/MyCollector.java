package baobao.java8.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author baobao
 * @create 2020-03-24 20:24
 * @description
 */
public class MyCollector<T> implements Collector<T, Set<T>,Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier()被调用");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator()被调用");
        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner()被调用");
        return (s1,s2) -> {
            s1.addAll(s2);
            return s1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher()被调用");
        return s -> s;
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics()被调用");
        //设置IDENTITY_FINISH、UNORDERED属性
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("curry", "thompson", "baobao");
        Set<String> set = list.stream().collect(new MyCollector<String>());
        System.out.println(set);
    }
}
