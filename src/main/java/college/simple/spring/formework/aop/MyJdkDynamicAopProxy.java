package college.simple.spring.formework.aop;

import college.simple.spring.formework.aop.intercept.MyMethodInvocation;
import college.simple.spring.formework.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/4/15
 * Time: 20:51
 * Version:V1.0
 */
public class MyJdkDynamicAopProxy implements MyAopProxy, InvocationHandler {

    private MyAdvisedSupport advisedSupport;

    public MyJdkDynamicAopProxy(MyAdvisedSupport config) {
        this.advisedSupport = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicMethodMatchers = this.advisedSupport.getInterceptorsAndDynamicInterceptionAdvice(method, this.advisedSupport.getTargetClass());
        MyMethodInvocation invocation = new MyMethodInvocation(proxy, this.advisedSupport.getTarget(), method, args,
                this.advisedSupport.getTargetClass(), interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }

    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advisedSupport.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advisedSupport.getTargetClass().getClassLoader());
    }
}
