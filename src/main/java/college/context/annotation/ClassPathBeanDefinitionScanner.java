package college.context.annotation;

import college.beans.factory.config.BeanDefinition;
import college.beans.factory.config.BeanDefinitionHolder;
import college.context.support.BeanDefinitionRegistry;
import college.core.ResourceLoader;
import college.core.env.Environment;
import college.core.env.StandardEnvironment;
import college.core.io.support.ResourcePatternResolver;
import college.core.type.CachingMetadataReaderFactory;
import college.utils.Assert;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: xuxianbei
 * Date: 2021/4/21
 * Time: 11:13
 * Version:V1.0
 */
public class ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;

    private ResourcePatternResolver resourcePatternResolver;

    private Environment environment;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this(registry, true);
    }

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        this(registry, useDefaultFilters, getOrCreateEnvironment(registry));
    }

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,
                                          Environment environment) {

        this(registry, useDefaultFilters, environment,
                (registry instanceof ResourceLoader ? (ResourceLoader) registry : null));
    }

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,
                                          Environment environment, ResourceLoader resourceLoader) {

        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        this.registry = registry;


        setEnvironment(environment);
        setResourceLoader(resourceLoader);
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
//        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
//        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
//        this.componentsIndex = CandidateComponentsIndexLoader.loadIndex(this.resourcePatternResolver.getClassLoader());
    }

    private static Environment getOrCreateEnvironment(BeanDefinitionRegistry registry) {
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        return new StandardEnvironment();
    }

    public void setEnvironment(Environment environment) {
        Assert.notNull(environment, "Environment must not be null");
        this.environment = environment;
    }

    //
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
        }
        return null;
    }

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        return scanCandidateComponents(basePackage);
    }

    private Set<BeanDefinition> scanCandidateComponents(String basePackage) {
        return null;
    }
}
