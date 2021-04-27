package college.beans.factory.support;

import college.beans.factory.config.BeanDefinition;
import college.beans.factory.config.BeanPostProcessor;
import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.context.BeansException;
import college.context.support.BeanDefinitionRegistry;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 20:08
 * Version:V1.0
 */

@Data
public class DefaultListableBeanFactory extends AbstractBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);


    private String serializationId;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
    }

    @Override
    public void destroySingletons() {

    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
        for (String beanName : beanNames) {
            getBean(beanName);
        }

    }




    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            beanDefinitionMap.put(beanName, beanDefinition);
            beanDefinitionNames.add(beanName);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        return null;
    }
}
