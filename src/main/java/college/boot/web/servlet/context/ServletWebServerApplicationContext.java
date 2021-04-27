package college.boot.web.servlet.context;

import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.context.support.GenericWebApplicationContext;

/**
 * @author: xuxianbei
 * Date: 2021/4/19
 * Time: 16:59
 * Version:V1.0
 */
public class ServletWebServerApplicationContext extends GenericWebApplicationContext {


    @Override
    protected void onRefresh() {
        super.onRefresh();
        try {
//            createWebServer();
        }
        catch (Throwable ex) {
            throw new RuntimeException("Unable to start web server", ex);
        }
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
//        beanFactory.addBeanPostProcessor(new WebApplicationContextServletContextAwareProcessor(this));
//        beanFactory.ignoreDependencyInterface(ServletContextAware.class);
//        registerWebApplicationScopes();
    }
}
