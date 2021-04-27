package college.context;

import college.beans.factory.annotation.AnnotatedBeanDefinition;
import college.beans.factory.config.BeanDefinition;
import college.beans.factory.config.BeanDefinitionHolder;
import college.beans.factory.parsing.ProblemReporter;
import college.beans.factory.support.BeanNameGenerator;
import college.context.annotation.*;
import college.context.support.BeanDefinitionRegistry;
import college.core.ResourceLoader;
import college.core.env.Environment;
import college.core.type.AnnotationMetadata;
import college.core.type.MetadataReaderFactory;
import college.core.type.StandardAnnotationMetadata;
import com.sun.xml.internal.ws.api.databinding.MetadataReader;
import lombok.Getter;

import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author: xuxianbei
 * Date: 2021/4/23
 * Time: 16:02
 * Version:V1.0
 */
public class ConfigurationClassParser {

    private final ComponentScanAnnotationParser componentScanParser;

    private static final Predicate<String> DEFAULT_EXCLUSION_FILTER = className ->
            (className.startsWith("java.lang.annotation.") || className.startsWith("org.springframework.stereotype."));

    public ConfigurationClassParser(MetadataReaderFactory metadataReaderFactory,
                                    ProblemReporter problemReporter, Environment environment, ResourceLoader resourceLoader,
                                    BeanNameGenerator componentScanBeanNameGenerator, BeanDefinitionRegistry registry) {

        this.componentScanParser = new ComponentScanAnnotationParser(
                environment, resourceLoader, componentScanBeanNameGenerator, registry);
    }

    public void parse(Set<BeanDefinitionHolder> configCandidates) {
        for (BeanDefinitionHolder holder : configCandidates) {
            BeanDefinition bd = holder.getBeanDefinition();

            if (bd instanceof AnnotatedBeanDefinition) {
                parse(((AnnotatedBeanDefinition) bd).getMetadata(), holder.getBeanName());
            }
        }
    }

    protected final void parse(AnnotationMetadata metadata, String beanName) {
        processConfigurationClass(new ConfigurationClass(metadata, beanName), DEFAULT_EXCLUSION_FILTER);
    }


    private SourceClass asSourceClass(ConfigurationClass configurationClass, Predicate<String> filter) {
        AnnotationMetadata metadata = configurationClass.getMetadata();
        if (metadata instanceof StandardAnnotationMetadata) {
            return asSourceClass(((StandardAnnotationMetadata) metadata).getIntrospectedClass(), filter);
        }
        return null;
    }

    SourceClass asSourceClass(Class<?> classType, Predicate<String> filter) {
        return new SourceClass(classType);
    }

    protected void processConfigurationClass(ConfigurationClass configClass, Predicate<String> filter) {
        //这里已经拿到主类信息

        SourceClass sourceClass = asSourceClass(configClass, filter);
        do {
            sourceClass = doProcessConfigurationClass(configClass, sourceClass, filter);
        }
        while (sourceClass != null);
    }

    protected final SourceClass doProcessConfigurationClass(
            ConfigurationClass configClass, SourceClass sourceClass, Predicate<String> filter) {

        Set<AnnotationAttributes> componentScans = AnnotationConfigUtils.attributesForRepeatable(
                sourceClass.getMetadata(), ComponentScans.class, ComponentScan.class);
        for (AnnotationAttributes componentScan : componentScans) {
            Set<BeanDefinitionHolder> scannedBeanDefinitions =
                    this.componentScanParser.parse(componentScan, sourceClass.getMetadata().getClassName());
        }
        return null;
    }

    @Getter
    private class SourceClass {

        private final Object source;  // Class or MetadataReader

        private final AnnotationMetadata metadata;

        public SourceClass(Object source) {
            this.source = source;
            this.metadata = null;
//            if (source instanceof Class) {
//                this.metadata = AnnotationMetadata.introspect((Class<?>) source);
//            }
//            else {
//                this.metadata = ((MetadataReader) source).getAnnotationMetadata();
//            }
        }

    }
}
