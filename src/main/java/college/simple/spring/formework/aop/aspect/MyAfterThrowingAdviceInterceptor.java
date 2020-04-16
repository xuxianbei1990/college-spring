package college.simple.spring.formework.aop.aspect;

import college.simple.spring.formework.aop.intercept.MyMethodInterceptor;
import college.simple.spring.formework.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 15:18
 * Version:V1.0
 */
public class MyAfterThrowingAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {

    private String throwingName;

    public MyAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName){
        this.throwingName = throwName;
    }
}
