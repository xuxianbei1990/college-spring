package college.core.type;

import college.utils.Assert;
import lombok.Getter;

/**
 * @author: xuxianbei
 * Date: 2021/4/25
 * Time: 10:23
 * Version:V1.0
 */
@Getter
public class StandardClassMetadata {

    private final Class<?> introspectedClass;

    public StandardClassMetadata(Class<?> introspectedClass) {
        Assert.notNull(introspectedClass, "Class must not be null");
        this.introspectedClass = introspectedClass;
    }
}
