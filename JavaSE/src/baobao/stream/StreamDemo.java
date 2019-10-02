package baobao.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author baobao
 * @create 2019-09-12 23:43
 * @description 演示流的使用
 */
public class StreamDemo {

    public static void main(String[] args) {
        //一、创建流
        //1.通过Collection接口的默认方法
        //default Stream<E> stream() : 返回一个顺序流
        //default Stream<E> parallelStream() : 返回一个并行流

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Stream<Integer> stream1 = list.stream();

        //2.通过Arrays的静态方法
        //static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream2 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        //3.通过Stream的静态方法
        //public static<T> Stream<T> of(T... values) : 返回一个流
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

        List<User> userList = new ArrayList<>();
        userList.add(new User("baobao",18));
        userList.add(new User("Thompson",29));
        userList.add(new User("Curry",30));
        userList.add(new User("Durant",30));
        userList.add(new User("Lin",31));

        //二、流的中间操作
        Stream<User> userStream = userList.stream();
        //1.Stream<T> filter(Predicate<? super T> predicate):接收Lambda ，从流中排除某些元素
        //过滤出年龄大于等于30的user
        //userStream.filter(user -> user.getAge() >= 30).forEach(System.out::println);

        //2.Stream<T> limit(long maxSize):截断流，使其元素不超过给定数量
        //取出年龄大于等于30的前2个user
        //userStream.filter(user -> user.getAge() >= 30).limit(2).forEach(System.out::println);

        //3.<R> Stream<R> map(Function<? super T, ? extends R> mapper):接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
        //将user对象的流映射成userName的流
        //userStream.map(User::getUserName).forEach(System.out::println);

        ////将user对象的流映射成age的流
        //userStream.mapToInt(User::getAge).forEach(System.out::println);

        //4.<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper):接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        //将每个user的userName取出来转换成char字符的流，再将这些char数组的流合并成一个流
        //userStream.flatMap(user -> Arrays.stream(user.getUserName().split(""))).forEach(System.out::println);

        //5.Stream<T> sorted():产生一个新流，其中按自然顺序排序
        //将user流映射成age流并且排序
        //userStream.mapToInt(User::getAge).sorted().forEach(System.out::println);

        //Stream<T> sorted(Comparator<? super T> comparator):产生一个新流，其中按比较器顺序排序
        //将user按照age的进行倒序排序后形成新的user流
        //userStream.sorted((u1,u2) -> u2.getAge() - u1.getAge()).forEach(System.out::println);

        //将user先按照年龄排序，如果年龄相同再按照userName倒序排序，排序后形成新流
        //userStream.sorted(Comparator.comparing(User::getAge).thenComparing((u1,u2) -> u2.getUserName().compareTo(u1.getUserName()))).
        //        forEach(System.out::println);

        //三、流的终止操作
        //boolean allMatch(Predicate<? super T> predicate):检查是否匹配所有元素
        //是否所有user的age都大于30
        //System.out.println(userStream.allMatch(user -> user.getAge() > 30));

        //boolean anyMatch(Predicate<? super T> predicate):检查是否至少匹配一个元素
        //是否有任意一个user的age大于30
        //System.out.println(userStream.anyMatch(user -> user.getAge() > 30));

        //Optional<T> findFirst():返回第一个元素
        //System.out.println(userStream.findFirst());

        //long count():返回流中元素总数
        //System.out.println(userStream.count());

        //Optional<T> max(Comparator<? super T> comparator):返回流中最大值
        //返回user中按照年龄倒序排序后的最大值(年龄最小的那个)
        //System.out.println(userStream.max((u1,u2) -> u2.getAge() - u1.getAge()));

        //void forEach(Consumer<? super T> action):内部迭代
        //打印流中所有user的值
        //userStream.forEach(user -> System.out.println(user));

        //归约
        // T reduce(T identity, BinaryOperator<T> accumulator):可以将流中元素反复结合起来，得到一个值
        //将user流映射成age流之后，对age流中的所有值求和
        //System.out.println(userStream.map(User::getAge).reduce(0, (x,y) -> x + y));

        //收集
        //<R, A> R collect(Collector<? super T, A, R> collector):将流转换为其他形式，接收一个collector接口，可以用Collectors工具类生成接口实现类
        //将user流转换为list
        //System.out.println(userStream.collect(Collectors.toList()));

        //将user流映射成userName流之后再用","连接所有userName并返回对应的String
        //System.out.println(userStream.map(User::getUserName).collect(Collectors.joining(",")));

        //对user流的所有user对象的age进行求和
        System.out.println(userStream.collect(Collectors.summingInt(User::getAge)));


        //数值统计
        //IntSummaryStatistics summaryStatistics():对IntStream的数据进行各种统计
        IntSummaryStatistics statistics = userList.stream().mapToInt(User::getAge).summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getSum());
        System.out.println(statistics.getAverage());


    }
}

class User{
    private String userName;
    private int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
