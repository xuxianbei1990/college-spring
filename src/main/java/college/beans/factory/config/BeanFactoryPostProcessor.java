package college.beans.factory.config;

import college.context.BeansException;

/**
 * @author: xuxianbei
 * Date: 2021/4/20
 * Time: 14:43
 * Version:V1.0
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.

     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
