package college.core.type;

/**
 * @author: xuxianbei
 * Date: 2021/4/25
 * Time: 10:21
 * Version:V1.0
 */
public class StandardAnnotationMetadata extends StandardClassMetadata implements AnnotationMetadata {


    public StandardAnnotationMetadata(Class<?> introspectedClass) {
        super(introspectedClass);
    }

    @Override
    public String getClassName() {
        return null;
    }
}
