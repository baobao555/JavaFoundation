package baobao.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author baobao
 * @date 2019/7/16 0016 10:34
 * @description 演示JDK8之前的日期api
 */
public class DateDemo {
    public static void main(String[] args) throws ParseException {
        //1、演示java.util.date
        System.out.println("演示Date：");
        //无参构造方法，表示当前日期时间
        Date date1 = new Date();
        //带参构造方法，返回代表1970-1-1后经过多少毫秒的时间
        Date date2 = new Date(1000);
        System.out.println(date1);
        System.out.println(date2);
        //getTime方法 返回自1970年1月1日 00:00:00 GMT以来此Date对象表示的毫秒数
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());

        System.out.println("-----------------------------");
        System.out.println("演示SimpleDateFormat：");
        //2、演示SimpleDateFormat
        //创建SimpleDateFormat对象，传入格式化字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将Date对象格式化为字符串
        String dateString = sdf.format(new Date());
        System.out.println(dateString);
        //将字符串转化为Date对象
        Date date = sdf.parse("2019-5-27");
        System.out.println(date);

        System.out.println("-----------------------------");

        //3、演示Calendar
        System.out.println("演示Calendar：");
        //得到Calendar对象
        Calendar calendar = Calendar.getInstance();
        //利用Calendar对象得到当前时间
        Date time = calendar.getTime();
        System.out.println(time);

        //给Calendar对象设置指定的时间
        calendar.setTime(new Date(1000));
        //通过Calendar对象得到各种时间信息的字段并打印
        System.out.println(calendar.getTime());
        System.out.println("YEAR" + ":" + calendar.get(Calendar.YEAR));//年
        System.out.println("MONTH" + ":" + calendar.get(Calendar.MONTH));//月，0~11
        System.out.println("DATE" + ":" + calendar.get(Calendar.DATE));//日
        System.out.println("DAY_OF_MONTH" + ":" + calendar.get(Calendar.DAY_OF_MONTH));//一个月中的第几天
        System.out.println("DAY_OF_WEEK" + ":" + calendar.get(Calendar.DAY_OF_WEEK));//一周中的第几天

        //给字段设置指定的值
        calendar.set(Calendar.YEAR,2017);//设置年份为2017
        System.out.println(calendar.getTime());

        calendar.add(Calendar.YEAR,2);//在2017年基础上加2年
        System.out.println(calendar.getTime());
    }
}
