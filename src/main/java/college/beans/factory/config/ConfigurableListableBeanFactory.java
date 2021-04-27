package college.beans.factory.config;

import college.context.BeansException;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 20:00
 * Version:V1.0
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory{


    /**
     * 非延迟加载的类全部初始化
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
