package baobao.enumation;


/**
 * @author baobao
 * @date 2019/7/2 0002 16:27
 * @description 枚举测试
 */
public class EnumationTest {
    public static void main(String[] args) {
        System.out.println("测试自己手工实现的枚举类");
        MyEnumation spring = MyEnumation.Spring;
        MyEnumation summer = MyEnumation.Summer;
        MyEnumation autumn = MyEnumation.Autumn;
        MyEnumation winter = MyEnumation.Winter;

        System.out.println(spring.getName() + ":" + spring.getDesc());
        System.out.println(summer.getName() + ":" + summer.getDesc());
        System.out.println(autumn.getName() + ":" + autumn.getDesc());
        System.out.println(winter.getName() + ":" + winter.getDesc());

        System.out.println("--------------------------------------");
        System.out.println("测试系统enum实现的枚举类");
        Enumation spring1 = Enumation.Spring;
        Enumation summer1 = Enumation.Summer;
        Enumation autumn1 = Enumation.Autumn;
        Enumation winter1 = Enumation.Winter;

        System.out.println(spring1.name() + "-" + spring1.ordinal() + "-" + spring1.getName() + "-" + spring1.getDesc());
        System.out.println(summer1.name() + "-" + summer1.ordinal() + "-" + summer1.getName() + "-" + summer1.getDesc());
        System.out.println(autumn1.name() + "-" + autumn1.ordinal() + "-" + autumn1.getName() + "-" + autumn1.getDesc());
        System.out.println(winter1.name() + "-" + winter1.ordinal() + "-" + winter1.getName() + "-" + winter1.getDesc());

        System.out.println("--------------------------------------");
        System.out.println("测试系统enum的方法");

        Enumation[] values = Enumation.values();
        for (Enumation value : values) {
            System.out.println(value.getName() + ":" + value.getDesc());
        }
    }
}
