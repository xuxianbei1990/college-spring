package college.context.annotation;

import college.beans.factory.config.BeanDefinition;
import college.beans.factory.config.BeanDefinitionHolder;
import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.beans.factory.parsing.FailFastProblemReporter;
import college.beans.factory.parsing.ProblemReporter;
import college.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import college.beans.factory.support.BeanNameGenerator;
import college.context.BeansException;
import college.context.ConfigurationClassParser;
import college.context.support.BeanDefinitionRegistry;
import college.core.ResourceLoader;
import college.core.env.Environment;
import college.core.io.support.DefaultResourceLoader;
import college.core.type.CachingMetadataReaderFactory;
import college.core.type.MetadataReaderFactory;

import java.util.*;

/**
 * 这个类就是实现读取配置路径下的class文件
 * @author: xuxianbei
 * Date: 2021/4/22
 * Time: 18:25
 * Version:V1.0
 */
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

    private final Set<Integer> registriesPostProcessed = new HashSet<>();

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    private Environment environment;

    private ProblemReporter problemReporter = new FailFastProblemReporter();

    private BeanNameGenerator componentScanBeanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        //由对象直接获取hashcode。 判断是否重复加载
        int registryId = System.identityHashCode(registry);
        if (this.registriesPostProcessed.contains(registryId)) {
            throw new IllegalStateException(
                    "postProcessBeanDefinitionRegistry already called on this post-processor against " + registry);
        }
        this.registriesPostProcessed.add(registryId);

        processConfigBeanDefinitions(registry);
    }

    public void processConfigBeanDefinitions(BeanDefinitionRegistry registry) {
        List<BeanDefinitionHolder> configCandidates = new ArrayList<>();
        String[] candidateNames = registry.getBeanDefinitionNames();
        for (String beanName : candidateNames) {
            BeanDefinition beanDef = registry.getBeanDefinition(beanName);
            configCandidates.add(new BeanDefinitionHolder(beanDef, beanName));
        }

        ConfigurationClassParser parser = new ConfigurationClassParser(
                this.metadataReaderFactory, this.problemReporter, this.environment,
                this.resourceLoader, this.componentScanBeanNameGenerator, registry);

        Set<BeanDefinitionHolder> candidates = new LinkedHashSet<>(configCandidates);

        parser.parse(candidates);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
