package college.context;

import college.context.support.AbstractApplicationContext;
import college.core.env.ConfigurableEnvironment;
import org.springframework.beans.BeansException;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 17:52
 * Version:V1.0
 */
public class AnnotationConfigServletWebServerApplicationContext extends AbstractApplicationContext implements ConfigurableApplicationContext {

    @Override
    public void setEnvironment(ConfigurableEnvironment environment) {

    }

    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {

    }
}
