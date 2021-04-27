package college.context.support;

import college.beans.factory.config.BeanFactoryPostProcessor;
import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.beans.factory.support.DefaultListableBeanFactory;
import college.context.BeansException;
import college.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 19:48
 * Version:V1.0
 */
public abstract class AbstractApplicationContext {

    /**
     * Display name.
     */
    private String displayName = ObjectUtils.identityToString(this);

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    private String id = ObjectUtils.identityToString(this);

    public void refresh() throws BeansException, IllegalStateException {
        //准备一些事件和监听
        prepareRefresh();
        //DefaultListableBeanFactory
        //这里是把指定路径下的类转换成为BeanDefinitions
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

        //设置一些杂七杂八的东西，也不知道干啥
        //        prepareBeanFactory(beanFactory);
        //        try {
        //  按照 AnnotationConfigServletWebServerApplicationContext
        // 这里是注册了bean工厂的处理器
        postProcessBeanFactory(beanFactory);
        // 使用代理模式执行处理器得到BeanDefinition
        invokeBeanFactoryPostProcessors(beanFactory);

        // Initialize message source for this context.
        initMessageSource();

        // Initialize event multicaster for this context.
        initApplicationEventMulticaster();

        // Initialize other special beans in specific context subclasses.
        onRefresh();

        // Check for listener beans and register them.
        registerListeners();

        // Instantiate all remaining (non-lazy-init) singletons.
        // 非延迟的实例在这里实例化。
        finishBeanFactoryInitialization(beanFactory);

        //        }
    }

    protected void onRefresh() throws BeansException {
        // For subclasses: do nothing by default.
    }

    protected void prepareRefresh() {
    }

    protected void registerListeners() {

    }

    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.preInstantiateSingletons();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

    //    @Override
    public String getId() {
        return this.id;
    }

    protected void initMessageSource() {

    }

    protected void initApplicationEventMulticaster() {

    }

    public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return getBeanFactory();
    }

    //    @Override
    protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;

    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    }

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //这个其实就是spring 一个设计思想，组装条件，然后统一执行  这个也可以借鉴过来
        PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
    }

    public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessors() {
        return this.beanFactoryPostProcessors;
    }
}
