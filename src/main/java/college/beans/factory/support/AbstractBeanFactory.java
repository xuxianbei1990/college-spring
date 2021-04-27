package college.beans.factory.support;

import college.context.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xuxianbei
 * Date: 2021/4/26
 * Time: 13:54
 * Version:V1.0
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport {

    private final Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);


    //    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null, null, false);
    }

    protected String transformedBeanName(String name) {
        return null;
    }

    protected <T> T doGetBean(
            String name, Class<T> requiredType, Object[] args, boolean typeCheckOnly)
            throws BeansException {
        String beanName = transformedBeanName(name);
        RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
        Object sharedInstance = getSingleton(beanName);
        //是否单例
        if (mbd.isSingleton()) {
            sharedInstance = getSingleton(beanName, () -> {
                try {
                    return createBean(beanName, mbd, args);
                }
                catch (BeansException ex) {
                    // Explicitly remove instance from singleton cache: It might have been put there
                    // eagerly by the creation process, to allow for circular reference resolution.
                    // Also remove any beans that received a temporary reference to the bean.
//                    destroySingleton(beanName);
                    throw ex;
                }
            });
//            bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
        }
        return null;
    }


    protected abstract Object createBean(String beanName, RootBeanDefinition mbd,  Object[] args);

    protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) throws BeansException {
        // Quick check on the concurrent map first, with minimal locking.
        RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
        return null;
    }

}
