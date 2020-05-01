package baobao.jvm.classloader.myclassloader;

/**
 * @author baobao
 * @create 2020-04-05 22:51
 * @description
 */
public class Person {
    private Person person;

    public void setPerson(Object object){
        //将传入的Object强转为Person
        this.person = (Person) object;
    }
}
