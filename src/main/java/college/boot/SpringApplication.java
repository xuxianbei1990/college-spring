package college.boot;

import college.context.AnnotationConfigServletWebServerApplicationContext;
import college.context.ApplicationContext;
import college.context.ConfigurableApplicationContext;
import college.context.support.StandardServletEnvironment;
import college.core.ResourceLoader;
import college.core.env.ConfigurableEnvironment;
import college.utils.StopWatch;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 16:20
 * Version:V1.0
 */
public class SpringApplication {

    private ResourceLoader resourceLoader;

    private Set<Class<?>> primarySources;

    private WebApplicationType webApplicationType;

    private Class<?> mainApplicationClass;

    public SpringApplication(Class<?>... primarySources) {
        this(null, primarySources);
    }

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return run(new Class<?>[]{primarySource}, args);
    }

    public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
        return new SpringApplication(primarySources).run(args);
    }

    public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
        this.resourceLoader = resourceLoader;
        Assert.notNull(primarySources, "PrimarySources must not be null");
        this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
        //spring boot 默认启动方式就是 嵌入式 响应式 的web 服务器
        this.webApplicationType = WebApplicationType.SERVLET;
        //加载初始化工厂； 加载所有的监听器
//        setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
//        setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
        //就是拿到主函数： CollegeSpringApplication.class;
        this.mainApplicationClass = primarySources[0];
    }

    private <T> Collection<T> getSpringFactoriesInstances(Class<T> type) {
        return getSpringFactoriesInstances(type, new Class<?>[]{});
    }

    public ConfigurableApplicationContext run(String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ConfigurableApplicationContext context = null;
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
        //开启各种监听事件
        SpringApplicationRunListeners listeners = getRunListeners(args);
        listeners.starting();

        try {
            //就是把原始的参数封装了下，也不知道干啥
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
            //忽略某些bean
//            configureIgnoreBeanInfo(environment);
            Banner printedBanner = printBanner(environment);
            context = createApplicationContext();
            exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
                    new Class[]{ConfigurableApplicationContext.class}, context);
            prepareContext(context, environment, listeners, applicationArguments, printedBanner);
            refreshContext(context);
//            afterRefresh(context, applicationArguments);
//            stopWatch.stop();
//            if (this.logStartupInfo) {
//                new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
//            }
//            listeners.started(context);
//            callRunners(context, applicationArguments);
        } catch (Throwable ex) {
//            handleRunFailure(context, ex, exceptionReporters, listeners);
            throw new IllegalStateException(ex);
        }
        return null;
    }

    private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment,
                                SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
        context.setEnvironment(environment);
//        postProcessApplicationContext(context);
        //初始化
//        applyInitializers(context);
        listeners.contextPrepared(context);

        load(context, null);
    }

    private void refreshContext(ConfigurableApplicationContext context) {
        refresh(context);

    }

    protected void refresh(ApplicationContext applicationContext) {
        Assert.isInstanceOf(AbstractApplicationContext.class, applicationContext);
        ((AbstractApplicationContext) applicationContext).refresh();
    }

    protected void load(ApplicationContext context, Object[] sources) {
        BeanDefinitionLoader loader = createBeanDefinitionLoader(getBeanDefinitionRegistry(context), sources);
    }

    private BeanDefinitionRegistry getBeanDefinitionRegistry(ApplicationContext context) {
        return (BeanDefinitionRegistry) context;
    }

    protected BeanDefinitionLoader createBeanDefinitionLoader(BeanDefinitionRegistry registry, Object[] sources) {
        return new BeanDefinitionLoader(registry, sources);
    }

    protected ConfigurableApplicationContext createApplicationContext() {
        return new AnnotationConfigServletWebServerApplicationContext();
    }

    private Banner printBanner(ConfigurableEnvironment environment) {
        return null;
    }

    private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
                                                       ApplicationArguments applicationArguments) {
        ConfigurableEnvironment environment = new StandardServletEnvironment();
        return environment;
    }

    private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
        ClassLoader classLoader = type.getClassLoader();
        // Use names and ensure unique to protect against duplicates
        Set<String> names = new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
//        List<T> instances = createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
//        AnnotationAwareOrderComparator.sort(instances);
        return null;
    }

    private SpringApplicationRunListeners getRunListeners(String[] args) {
        return new SpringApplicationRunListeners();
    }
}
