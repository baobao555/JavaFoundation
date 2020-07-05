package baobao.jvm.stringtable;

/**
 * @author baobao
 * @create 2020-05-08 20:01
 * @description
 */
public class StringTableDemo2 {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        String s5 = "a" + "b";
        System.out.println(s3 == s4);//false
        System.out.println(s3 == s5);//true
        System.out.println(s4 == s5);//false
    }
}
