package college.beans.factory.support;

/**
 * @author: xuxianbei
 * Date: 2021/4/22
 * Time: 18:23
 * Version:V1.0
 */
public class RootBeanDefinition extends AbstractBeanDefinition {

    public RootBeanDefinition(Class<?> beanClass) {
        super();
        setBeanClass(beanClass);
    }
}
