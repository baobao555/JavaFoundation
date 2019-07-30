package baobao.annotation;

/**
 * @author baobao
 * @date 2019/7/17 0017 11:14
 * @description Person类的bean
 */
public class Person {
    //给属性name添加注解，代表对应json对象的名为"1"
    @MyAnnotation(jsonFieldName = "1")
    private String name;
    //给属性age添加注解，代表对应json对象的名为"2"
    @MyAnnotation(jsonFieldName = "2")
    private int age;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
