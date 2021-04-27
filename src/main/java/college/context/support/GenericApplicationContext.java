package college.context.support;

import college.beans.factory.BeanDefinitionStoreException;
import college.beans.factory.config.BeanDefinition;
import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.beans.factory.support.DefaultListableBeanFactory;
import college.context.BeansException;

/**
 * @author: xuxianbei
 * Date: 2021/4/20
 * Time: 14:26
 * Version:V1.0
 */
public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {

    private final DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {
//        if (!this.refreshed.compareAndSet(false, true)) {
//            throw new IllegalStateException(
//                    "GenericApplicationContext does not support multiple refresh attempts: just call 'refresh' once");
//        }
        this.beanFactory.setSerializationId(getId());
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException {
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }
}
