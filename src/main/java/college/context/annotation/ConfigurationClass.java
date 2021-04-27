package college.context.annotation;

import college.core.io.support.DescriptiveResource;
import college.core.io.support.Resource;
import college.core.type.AnnotationMetadata;
import college.utils.Assert;
import lombok.Getter;

/**
 * @author: xuxianbei
 * Date: 2021/4/23
 * Time: 16:22
 * Version:V1.0
 */
@Getter
public class ConfigurationClass {

    /**
     * 元数据
     */
    private final AnnotationMetadata metadata;

    private final Resource resource;

    private String beanName;

    public ConfigurationClass(AnnotationMetadata metadata, String beanName) {
        Assert.notNull(beanName, "Bean name must not be null");
        this.metadata = metadata;
        this.resource = new DescriptiveResource(metadata.getClassName());
        this.beanName = beanName;
    }

}
