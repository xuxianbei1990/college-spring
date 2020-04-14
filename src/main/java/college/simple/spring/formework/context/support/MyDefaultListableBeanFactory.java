package college.simple.spring.formework.context.support;

import college.simple.spring.formework.beans.config.MyBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 20:43
 * Version:V1.0
 */
public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {
    //存储注册信息的BeanDefinition,伪IOC容器
    protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap();
}
