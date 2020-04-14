package college.simple.spring.formework.beans.config;

/**
 * @author: xuxianbei
 * Date: 2020/4/13
 * Time: 15:05
 * Version:V1.0
 */
public class MyBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
