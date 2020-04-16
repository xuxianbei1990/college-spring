package college.simple.spring.formework.aop.intercept;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 15:42
 * Version:V1.0
 */
public interface MyMethodInterceptor {
    Object invoke(MyMethodInvocation invocation) throws Throwable;
}
