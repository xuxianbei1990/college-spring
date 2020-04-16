package college.simple.spring.formework.aop.aspect;

import college.simple.spring.formework.aop.intercept.MyMethodInterceptor;
import college.simple.spring.formework.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 15:17
 * Version:V1.0
 */
public class MyAfterReturningAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {

    private MyJoinPoint joinPoint;

    public MyAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.joinPoint = invocation;
        this.afterReturning(retVal, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint, retVal, null);
    }
}
