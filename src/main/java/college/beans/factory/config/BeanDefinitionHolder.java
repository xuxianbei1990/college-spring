package college.beans.factory.config;

import college.beans.factory.support.RootBeanDefinition;
import college.utils.Assert;
import lombok.Getter;

/**
 * @author: xuxianbei
 * Date: 2021/4/22
 * Time: 18:20
 * Version:V1.0
 */
@Getter
public class BeanDefinitionHolder {
    private final BeanDefinition beanDefinition;

    private final String beanName;

    private final String[] aliases;

    public BeanDefinitionHolder(RootBeanDefinition beanDefinition, String beanName) {
        this(beanDefinition, beanName, null);
    }

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
        this(beanDefinition, beanName, null);
    }

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName, String[] aliases) {
        Assert.notNull(beanDefinition, "BeanDefinition must not be null");
        Assert.notNull(beanName, "Bean name must not be null");
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
        this.aliases = aliases;
    }
}
