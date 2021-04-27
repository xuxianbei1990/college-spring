package college.beans.factory.annotation;

import college.beans.factory.config.BeanDefinition;
import college.core.type.AnnotationMetadata;

/**
 * @author: xuxianbei
 * Date: 2021/4/23
 * Time: 16:19
 * Version:V1.0
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetadata getMetadata();
}
