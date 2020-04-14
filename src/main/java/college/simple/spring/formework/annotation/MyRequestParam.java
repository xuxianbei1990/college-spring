package college.simple.spring.formework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 9:53
 * Version:V1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequestParam {
    String value() default "";
    boolean required() default true;
}
