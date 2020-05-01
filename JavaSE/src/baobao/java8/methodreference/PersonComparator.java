package baobao.java8.methodreference;

/**
 * @author baobao
 * @create 2020-03-12 20:11
 * @description
 */
public class PersonComparator {
    //实例方法，年龄正序比较
    public int compareByAge(Person p1,Person p2){
        return p1.getAge() - p2.getAge();
    }

    //实例方法，名字正序比较
    public int compareByName(Person p1,Person p2){
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}
