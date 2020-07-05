package baobao.jvm.stringtable;

/**
 * @author baobao
 * @create 2020-05-08 20:30
 * @description
 */
public class StringTableDemo3 {
    public static void main(String[] args) {
        testIntern2();
    }

    public static void testIntern2(){
        //会将"ab"放入StringTable
        String s1 = "ab";
        //动态创建一个"ab"，并不会放入StringTable
        String s2 = new String("a") + new String("b");
        //将s2主动加入到StringTable，但是由于StringTable中已经有值为"ab"的字符串，
        //所以s2不会被放到StringTable中，返回的s3是StringTable中的s1
        String s3 = s2.intern();
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s3);//true
        System.out.println(s1 == s2);//false
    }

    public static void testIntern1(){
        //动态创建一个"ab"，并不会放入StringTable
        String s1 = new String("a") + new String("b");
        //将s1主动加入到StringTable，返回的s2就是StringTable中的s1
        String s2 = s1.intern();
        System.out.println(s1 == s2);
    }
}
