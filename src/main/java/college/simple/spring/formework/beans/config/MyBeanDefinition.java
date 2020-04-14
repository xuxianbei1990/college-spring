package college.simple.spring.formework.beans.config;

import lombok.Data;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 20:44
 * Version:V1.0
 */
@Data
public class MyBeanDefinition {
    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;
    private boolean isSingleton = true;
}
