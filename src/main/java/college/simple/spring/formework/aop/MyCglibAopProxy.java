package college.simple.spring.formework.aop;

import college.simple.spring.formework.aop.support.MyAdvisedSupport;

/**
 * @author: xuxianbei
 * Date: 2020/4/15
 * Time: 20:50
 * Version:V1.0
 */
public class MyCglibAopProxy implements MyAopProxy {
    public MyCglibAopProxy(MyAdvisedSupport config) {

    }

    @Override
    public Object getProxy() {
        return null;
    }
}
