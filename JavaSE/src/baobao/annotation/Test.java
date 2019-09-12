package baobao.annotation;

/**
 * @author baobao
 * @date 2019/7/30 0030 10:52
 * @description
 */
@TestAnnotation(1)
public class Test {
    @TestAnnotation(2)
    private int i;
    @TestAnnotation(3)
    private String s;
}

@interface TestAnnotation{
   int value();
}