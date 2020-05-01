package baobao.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baobao
 * @create 2020-03-18 22:28
 * @description
 */
public class GenericDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //list.add(1);
    }
}

class StringData implements Data<String>{
    @Override
    public void add(String s) {

    }
}

class IntegerData implements Data<Integer>{
    @Override
    public void add(Integer integer) {

    }
}

interface Data<T>{
    void add(T t);
}
