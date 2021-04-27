package college.beans.factory.support;

import college.beans.factory.BeanWrapper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: xuxianbei
 * Date: 2021/4/27
 * Time: 11:27
 * Version:V1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private final ConcurrentMap<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        RootBeanDefinition mbdToUse = mbd;

        Class<?> resolvedClass = resolveBeanClass(mbd, beanName);

        Object beanInstance = doCreateBean(beanName, mbdToUse, args);
        return null;
    }


    protected Object doCreateBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        BeanWrapper instanceWrapper = null;

        if (mbd.isSingleton()) {
            instanceWrapper = this.factoryBeanInstanceCache.remove(beanName);
        }
        if (instanceWrapper == null) {
            //BeanWrapper 应该是做适配器作用
            instanceWrapper = createBeanInstance(beanName, mbd, args);
        }

        populateBean(beanName, mbd, instanceWrapper);
        return null;
    }

    protected void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bw) {

    }

    protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd,Object[] args) {
        return null;
    }

    protected Class<?> resolveBeanClass(RootBeanDefinition mbd, String beanName, Class<?>... typesToMatch) {
        if (mbd.hasBeanClass()) {
            return (Class<?>) mbd.getBeanClass();
        }

        return null;
    }
}
