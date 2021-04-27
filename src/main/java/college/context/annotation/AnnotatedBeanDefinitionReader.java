package college.context.annotation;

import college.context.support.BeanDefinitionRegistry;
import college.core.env.Environment;
import college.core.env.StandardEnvironment;

/**
 * @author: xuxianbei
 * Date: 2021/4/21
 * Time: 11:13
 * Version:V1.0
 */
public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, getOrCreateEnvironment(registry));
    }

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry, Environment environment) {
        this.registry = registry;
//        this.conditionEvaluator = new ConditionEvaluator(registry, environment, null);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
    }

    private static Environment getOrCreateEnvironment(BeanDefinitionRegistry registry) {
        return new StandardEnvironment();
    }
}
