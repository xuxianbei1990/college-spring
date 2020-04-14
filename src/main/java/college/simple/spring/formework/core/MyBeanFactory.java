package college.simple.spring.formework.core;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 20:41
 * Version:V1.0
 */
public interface MyBeanFactory {
    Object getBean(String beanName) throws Exception;

    Object getBean(Class<?> beanClass) throws Exception;
}
