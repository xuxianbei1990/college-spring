package college.simple.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 9:54
 * Version:V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
