package college.context;

import college.core.env.ConfigurableEnvironment;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 16:21
 * Version:V1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void setEnvironment(ConfigurableEnvironment environment);
}
