package baobao.jvm.stringtable;

import java.util.stream.IntStream;

/**
 * @author baobao
 * @create 2020-05-08 20:56
 * @description
 */
public class StringTableDemo4 {
    public static void main(String[] args) {
        IntStream.range(0, 100000).forEach(i -> {
            //不断创建新的String对象往StringTable中放
            String.valueOf(i).intern();
        });
    }
}
