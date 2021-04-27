package college.context.annotation;

import college.beans.factory.support.BeanNameGenerator;

/**
 * @author: xuxianbei
 * Date: 2021/4/23
 * Time: 16:14
 * Version:V1.0
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    public static final AnnotationBeanNameGenerator INSTANCE = new AnnotationBeanNameGenerator();
}
