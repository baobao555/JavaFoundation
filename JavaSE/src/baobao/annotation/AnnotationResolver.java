package baobao.annotation;

import java.lang.reflect.Field;

/**
 * @author baobao
 * @date 2019/7/17 0017 11:17
 * @description 解析自定义注解
 */
public class AnnotationResolver {
    public static void main(String[] args) throws Exception {
        //反射Person类
        Class<?> clazz = Class.forName("baobao.annotation.Person");
        Object instance = clazz.newInstance();
        //遍历Person的所有属性
        for (Field field : clazz.getDeclaredFields()) {
            //打印属性名
            System.out.println("field = " + field.getName());
            //判断属性上是否有MyAnnotation注解
            if (field.isAnnotationPresent(MyAnnotation.class)){
                //得到注解
                MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                //得到注解中属性的值
                String jsonFieldName = annotation.jsonFieldName();
                System.out.println("jsonFieldName = " + jsonFieldName);

                //进行json属性映射到Person bean属性的操作。。。。
            }
        }
    }
}
