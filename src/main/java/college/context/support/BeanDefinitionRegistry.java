package college.context.support;

import college.beans.factory.BeanDefinitionStoreException;
import college.beans.factory.config.BeanDefinition;

/**
 * @author: xuxianbei
 * Date: 2021/4/19
 * Time: 15:45
 * Version:V1.0
 */
public interface BeanDefinitionRegistry {

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException;

    BeanDefinition getBeanDefinition(String beanName);
}
