package college.context.support;

import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.BeansException;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 19:48
 * Version:V1.0
 */
public abstract class AbstractApplicationContext {

    public void refresh() throws BeansException, IllegalStateException {
        //准备一些事件和监听
        prepareRefresh();
//DefaultListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

        //设置一些杂七杂八的东西，也不知道干啥
//        prepareBeanFactory(beanFactory);
//        try {
        //扫描指定包下面的类
            postProcessBeanFactory(beanFactory);
            // 创建bean
        invokeBeanFactoryPostProcessors(beanFactory);

//        }
    }

    protected void prepareRefresh() {}

    protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return new DefaultListableBeanFactory();
    }

    protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;

    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    }

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
//        PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
    }
}
