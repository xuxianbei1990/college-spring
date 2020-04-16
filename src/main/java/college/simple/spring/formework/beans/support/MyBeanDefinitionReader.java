package college.simple.spring.formework.beans.support;

import college.simple.spring.formework.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: xuxianbei
 * Date: 2020/4/11
 * Time: 14:26
 * Version:V1.0
 */
public class MyBeanDefinitionReader {

    private Properties config = new Properties();

    private List<String> registyBeanClasses = new ArrayList();

    private final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader(String... locations) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registyBeanClasses.add(className);
            }
        }
    }


    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> result = new ArrayList();
        for (String className : registyBeanClasses) {
            try {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                Class<?> [] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private MyBeanDefinition doCreateBeanDefinition(String beanClassName, String factoryBeanName) {
        MyBeanDefinition beanDefinition = new MyBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        //在spring的框架体系中，bean都是由factory 创建的，这里就简化处理了
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }


    public Properties getConfig() {
        return config;
    }
}
