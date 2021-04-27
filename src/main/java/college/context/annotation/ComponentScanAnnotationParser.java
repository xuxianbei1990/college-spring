package college.context.annotation;

import college.beans.factory.config.BeanDefinitionHolder;
import college.beans.factory.support.BeanNameGenerator;
import college.context.support.BeanDefinitionRegistry;
import college.core.ResourceLoader;
import college.core.env.Environment;
import college.utils.Assert;
import college.utils.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: xuxianbei
 * Date: 2021/4/25
 * Time: 10:32
 * Version:V1.0
 */
public class ComponentScanAnnotationParser {

    private final Environment environment;

    private final ResourceLoader resourceLoader;

    private final BeanNameGenerator beanNameGenerator;

    private final BeanDefinitionRegistry registry;

    public ComponentScanAnnotationParser(Environment environment, ResourceLoader resourceLoader,
                                         BeanNameGenerator beanNameGenerator, BeanDefinitionRegistry registry) {

        this.environment = environment;
        this.resourceLoader = resourceLoader;
        this.beanNameGenerator = beanNameGenerator;
        this.registry = registry;
    }

    public Set<BeanDefinitionHolder> parse(AnnotationAttributes componentScan, final String declaringClass) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(this.registry,
                true, this.environment, this.resourceLoader);

        Set<String> basePackages = new LinkedHashSet<>();
        //这个先从注解ComponentScan 的 basePackages 获取
        //然后从定义 basePackageClasses 获取
        //最后从主类获取
        basePackages.add(getPackageName(declaringClass));

        return scanner.doScan(StringUtils.toStringArray(basePackages));
    }

    private static final char PACKAGE_SEPARATOR = '.';

    public static String getPackageName(String fqClassName) {
        Assert.notNull(fqClassName, "Class name must not be null");
        int lastDotIndex = fqClassName.lastIndexOf(PACKAGE_SEPARATOR);
        return (lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "");
    }
}
