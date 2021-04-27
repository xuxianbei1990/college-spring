package college.beans.factory.support;

import college.beans.factory.config.BeanDefinition;
import lombok.Data;

/**
 * @author: xuxianbei
 * Date: 2021/4/22
 * Time: 18:24
 * Version:V1.0
 */
@Data
public abstract class AbstractBeanDefinition implements BeanDefinition {

    private volatile Object beanClass;

    private int role = BeanDefinition.ROLE_APPLICATION;

//    @Override
    public boolean isSingleton() {
//        return SCOPE_SINGLETON.equals(this.scope) || SCOPE_DEFAULT.equals(this.scope);
        return true;
    }

    public boolean hasBeanClass() {
        return (this.beanClass instanceof Class);
    }
}
