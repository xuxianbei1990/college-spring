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
public class MyMethodBeforeAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {

    private MyJoinPoint joinPoint;

    public MyMethodBeforeAdviceInterceptor(Method method, Object newInstance) {
        super(method, newInstance);
    }

    private void before(Method method, Object[] args, Object target) throws Throwable {
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint, null, null);
    }

    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
