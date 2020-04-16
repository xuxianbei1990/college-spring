package college.simple.spring.formework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 15:45
 * Version:V1.0
 */
public interface MyJoinPoint {
    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
