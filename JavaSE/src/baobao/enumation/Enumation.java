package baobao.enumation;

/**
 * @author baobao
 * @date 2019/7/2 0002 16:36
 * @description 枚举类
 */
public enum Enumation {

    Spring("春天1","春暖花开1"),
    Summer("夏天1","炎炎夏日1"),
    Autumn("秋天1","秋高气爽1"),
    Winter("冬天1","冰天雪地1");
    private String name;
    private String desc;

    Enumation(String name, String desc) {
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
