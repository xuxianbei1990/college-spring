package college.simple.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 9:51
 * Version:V1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    String value() default "";
}
