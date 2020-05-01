package baobao.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author baobao
 * @date 2019/7/16 0016 11:22
 * @description 演示JDK8新的日期api
 */
public class JDK8NewDateApi {
    public static void main(String[] args) {
        //1、演示LocalTime
        System.out.println("演示LocalTime：");
        //获取当前时间
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime.now() " + localTime);
        //构造指定时间
        localTime = LocalTime.of(18,45,20);
        System.out.println("LocalTime.of(hour,minute,second) " + localTime);
        //获取时间对象中的小时、分钟、秒等信息
        System.out.println(localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond());


        System.out.println("================================");
        //2、演示LocalDate
        System.out.println("演示LocalDate：");
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate.now() " + localDate);
        //构造指定日期
        localDate = LocalDate.of(2018,5,31);
        System.out.println("LocalDate.of(year,month,dayOfMonth) " + localDate);
        //获取日期对象的年、月、日等信息
        System.out.println(localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth());

        System.out.println("================================");
        //3、演示LocalDateTime
        System.out.println("演示LocalDateTime：");
        //获取当前日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime.now() " + localDateTime);
        //构造指定日期时间
        localDateTime = LocalDateTime.of(2019, 5, 27, 8, 30,20);
        System.out.println("LocalDate.of(year,month,dayOfMonth,hour,minute,second) " + localDateTime);
        //通过LocalDate、LocalTime对象构造指定日期时间
        localDateTime = LocalDateTime.of(localDate,localTime);
        System.out.println("LocalDateTime.of(localDate,localTime) " + localDateTime);
        //修改LocalDateTime对象的信息并返回一个新的LocalDateTime对象
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(25);
        LocalDateTime localDateTime2 = localDateTime1.plusWeeks(5);
        System.out.println("修改后的localDateTime:" + localDateTime);
        System.out.println("修改localDateTime后得到的localDateTime1: " + localDateTime1);
        System.out.println("修改localDateTime1后得到的localDateTime2: " + localDateTime2);
        //获取LocalDateTime对象的信息
        System.out.println("localDateTime.getDayOfWeek() " + localDateTime1.getDayOfWeek());

        System.out.println("================================");
        //4、演示DateTimeFormatter
        System.out.println("演示DateTimeFormatter");
        //根据指定格式创建DateTimeFormatter对象
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //将localDateTime对象转化为字符串
        String formatString = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(formatString);
        //将字符串解析为LocalDate对象
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2019-07-07");
        System.out.println(LocalDate.from(temporalAccessor));

        System.out.println("================================");
        //5、演示Duration
        System.out.println("演示Duration");
        //计算两个LocalTime的时间差
        Duration duration = Duration.between(LocalTime.now(),LocalTime.of(18,20));
        System.out.println("duration:" + duration);

        //6、演示Period
        System.out.println("演示Period");
        //计算两个LocalDate的日期差
        Period period = Period.between(LocalDate.now(),LocalDate.of(2018,9,20));
        System.out.println("period:" + period);

    }
}
