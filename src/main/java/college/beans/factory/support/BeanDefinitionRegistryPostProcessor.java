package college.beans.factory.support;

import college.beans.factory.config.BeanFactoryPostProcessor;
import college.context.BeansException;
import college.context.support.BeanDefinitionRegistry;

/**
 * @author: xuxianbei
 * Date: 2021/4/23
 * Time: 15:34
 * Version:V1.0
 */
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {


    /**
     * 处理注册信息
     * @param registry
     * @throws BeansException
     */
    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;

}
