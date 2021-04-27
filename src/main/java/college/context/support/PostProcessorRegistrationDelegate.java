package college.context.support;

import college.beans.factory.config.BeanFactoryPostProcessor;
import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2021/4/20
 * Time: 14:42
 * Version:V1.0
 */
public class PostProcessorRegistrationDelegate {

    public static void invokeBeanFactoryPostProcessors(
            ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {
        //大体意思  beanFactoryPostProcessors 执行了 beanFactory。做了相关的处理
        if (beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

            List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();
            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
        }
    }

    private static void invokeBeanDefinitionRegistryPostProcessors(
            Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

        for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
            postProcessor.postProcessBeanDefinitionRegistry(registry);
        }
    }
}
