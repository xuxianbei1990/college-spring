package college.boot.web.servlet.context;

import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.context.BeansException;
import college.context.support.AbstractApplicationContext;

/**
 * @author: xuxianbei
 * Date: 2021/4/19
 * Time: 15:54
 * Version:V1.0
 */
public class AnnotationConfigServletWebServerApplicationContext extends ServletWebServerApplicationContext {
    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {

    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        super.postProcessBeanFactory(beanFactory);
//        if (this.basePackages != null && this.basePackages.length > 0) {
//            this.scanner.scan(this.basePackages);
//        }
//        if (!this.annotatedClasses.isEmpty()) {
//            this.reader.register(ClassUtils.toClassArray(this.annotatedClasses));
//        }
    }
}
