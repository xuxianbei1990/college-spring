package college.context.annotation;

import college.beans.factory.config.BeanDefinition;
import college.beans.factory.config.BeanDefinitionHolder;
import college.beans.factory.support.RootBeanDefinition;
import college.context.support.BeanDefinitionRegistry;
import college.core.type.AnnotationMetadata;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: xuxianbei
 * Date: 2021/4/22
 * Time: 18:19
 * Version:V1.0
 */
public class AnnotationConfigUtils {

    public static final String CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME =
            "org.springframework.context.annotation.internalConfigurationAnnotationProcessor";

    public static void registerAnnotationConfigProcessors(BeanDefinitionRegistry registry) {
        registerAnnotationConfigProcessors(registry, null);
    }

    public static Set<BeanDefinitionHolder> registerAnnotationConfigProcessors(
            BeanDefinitionRegistry registry, Object source) {
        Set<BeanDefinitionHolder> beanDefs = new LinkedHashSet<>(8);
        if (!registry.containsBeanDefinition(CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME)) {
            RootBeanDefinition def = new RootBeanDefinition(ConfigurationClassPostProcessor.class);
//            def.setSource(source);
            beanDefs.add(registerPostProcessor(registry, def, CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME));
        }
        return beanDefs;
    }

    private static BeanDefinitionHolder registerPostProcessor(
            BeanDefinitionRegistry registry, RootBeanDefinition definition, String beanName) {

        definition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(beanName, definition);
        return new BeanDefinitionHolder(definition, beanName);
    }

    public static Set<AnnotationAttributes> attributesForRepeatable(AnnotationMetadata metadata,
                                                                    Class<?> containerClass, Class<?> annotationClass) {

        //这里就是循环递归，从annotations 得到目标注解
        return null;
    }
}
