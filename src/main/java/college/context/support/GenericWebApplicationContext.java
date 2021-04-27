package college.context.support;

import college.beans.factory.config.ConfigurableListableBeanFactory;
import college.beans.factory.support.DefaultListableBeanFactory;
import college.context.BeansException;
import college.context.ConfigurableApplicationContext;
import college.core.env.ConfigurableEnvironment;

/**
 * @author: xuxianbei
 * Date: 2021/4/20
 * Time: 14:21
 * Version:V1.0
 */
public class GenericWebApplicationContext extends GenericApplicationContext implements ConfigurableApplicationContext {

    @Override
    protected void onRefresh() {
//        this.themeSource = UiApplicationContextUtils.initThemeSource(this);
    }

    @Override
    public void setEnvironment(ConfigurableEnvironment environment) {

    }
}
