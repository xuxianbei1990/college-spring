package college.beans.factory.config;

/**
 * @author: xuxianbei
 * Date: 2021/4/19
 * Time: 17:02
 * Version:V1.0
 */
public interface ConfigurableBeanFactory {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
