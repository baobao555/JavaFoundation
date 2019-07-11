package baobao.enumation;

/**
 * @author baobao
 * @date 2019/7/2 0002 16:17
 * @description 枚举类手动实现
 */
public class MyEnumation {

    public static MyEnumation Spring = new MyEnumation("春天","春暖花开");
    public static MyEnumation Summer = new MyEnumation("夏天","炎炎夏日");
    public static MyEnumation Autumn = new MyEnumation("秋天","秋高气爽");
    public static MyEnumation Winter = new MyEnumation("冬天","冰天雪地");
    private String name;
    private String desc;

    private MyEnumation(){}

    private MyEnumation(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
