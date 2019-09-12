package baobao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author baobao
 * @date 2019/7/17 0017 11:08
 * @description 演示自定义注解
 */

@Retention(RetentionPolicy.RUNTIME) //运行时保留
@Target(ElementType.FIELD)  //注解作用于类的成员变量
public @interface MyAnnotation {
    //定义jsonFieldName属性，代表bean属性名对应的json属性名
    String jsonFieldName() default "";
}
