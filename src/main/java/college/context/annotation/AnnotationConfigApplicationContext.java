package college.context.annotation;

import college.context.support.GenericApplicationContext;

/**
 * @author: xuxianbei
 * Date: 2021/4/21
 * Time: 11:11
 * Version:V1.0
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext {

    private final AnnotatedBeanDefinitionReader reader;

    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigApplicationContext() {
        this.reader = new AnnotatedBeanDefinitionReader(this);
        this.scanner = new ClassPathBeanDefinitionScanner(this);
    }
}
