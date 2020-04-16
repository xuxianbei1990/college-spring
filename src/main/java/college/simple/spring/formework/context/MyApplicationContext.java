package college.simple.spring.formework.context;

import college.simple.spring.formework.annotation.MyAutowired;
import college.simple.spring.formework.annotation.MyController;
import college.simple.spring.formework.annotation.MyService;
import college.simple.spring.formework.aop.MyAopProxy;
import college.simple.spring.formework.aop.MyCglibAopProxy;
import college.simple.spring.formework.aop.MyJdkDynamicAopProxy;
import college.simple.spring.formework.aop.config.MyAopConfig;
import college.simple.spring.formework.aop.support.MyAdvisedSupport;
import college.simple.spring.formework.beans.MyBeanWrapper;
import college.simple.spring.formework.beans.config.MyBeanDefinition;
import college.simple.spring.formework.beans.config.MyBeanPostProcessor;
import college.simple.spring.formework.beans.support.MyBeanDefinitionReader;
import college.simple.spring.formework.context.support.MyDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 10:04
 * Version:V1.0
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory {

    private String[] configLoactions;

    private MyBeanDefinitionReader reader;
    //单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap();

    //通用的IOC容器
    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap();


    public MyApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1、定位，定位配置文件
        reader = new MyBeanDefinitionReader(this.configLoactions);

        //2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面(伪IOC容器)
        doRegisterBeanDefinition(beanDefinitions);

        //4、把不是延时加载的类，有提前初始化
        doAutowrited();

        onRefresh();
    }

    protected void onRefresh() {

    }

    private void doAutowrited() {
        for (Map.Entry<String, MyBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            if (!entry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    public Object getBean(String beanName) throws Exception {
        MyBeanDefinition gpBeanDefinition = this.beanDefinitionMap.get(beanName);
        Object instance = null;
        //这里和源码出入较大
        MyBeanPostProcessor postProcessor = new MyBeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(instance, beanName);
        instance = instantiateBean(beanName, gpBeanDefinition);
        MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);
        factoryBeanInstanceCache.putIfAbsent(beanName, beanWrapper);
        postProcessor.postProcessAfterInitialization(instance, beanName);
        //3、注入
        populateBean(beanName, new MyBeanDefinition(), beanWrapper);

        return factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    //也就说spring的处理机制先把所有的实例创建好，然后分类。
    private void populateBean(String beanName, MyBeanDefinition myBeanDefinition, MyBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();
        Class<?> clazz = beanWrapper.getWrappedClass();

        if (!clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class)) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }
            MyAutowired autowired = field.getAnnotation(MyAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            if (factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                continue;
            }
            try {
                field.set(instance, factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始bean开始处理单例，作用域问题
     *
     * @param beanName
     * @param myBeanDefinition
     * @return
     */
    private Object instantiateBean(String beanName, MyBeanDefinition myBeanDefinition) {
        String className = myBeanDefinition.getBeanClassName();
        Object instance = null;
        try {
            if (factoryBeanObjectCache.containsKey(className)) {
                instance = factoryBeanObjectCache.get(className);
            } else {
                Class<?> clazz = Class.forName(beanName);
                instance = clazz.newInstance();

                MyAdvisedSupport support = instantionAopConfig(myBeanDefinition);
                support.setTargetClass(clazz);
                support.setTarget(instance);
                if(support.pointCutMatch()) {
                    instance = createProxy(support).getProxy();
                }

                factoryBeanObjectCache.putIfAbsent(className, instance);
                factoryBeanObjectCache.putIfAbsent(myBeanDefinition.getFactoryBeanName(), instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private MyAopProxy createProxy(MyAdvisedSupport config) {

        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return new MyJdkDynamicAopProxy(config);
        }
        return new MyCglibAopProxy(config);
    }

    private MyAdvisedSupport instantionAopConfig(MyBeanDefinition myBeanDefinition) {
        MyAopConfig config = new MyAopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new MyAdvisedSupport(config);
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) {
        beanDefinitions.forEach(myBeanDefinition -> {
            if (beanDefinitionMap.containsKey(myBeanDefinition.getFactoryBeanName())) {
//                throw new RuntimeException("The “" + myBeanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            beanDefinitionMap.putIfAbsent(myBeanDefinition.getFactoryBeanName(), myBeanDefinition);
        });

    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new  String[this.beanDefinitionMap.size()]);
    }
}
