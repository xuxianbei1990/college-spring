package college.simple.spring;

import college.simple.spring.demo.controller.DemoController;
import college.simple.spring.formework.context.MyApplicationContext;
import college.simple.spring.formework.web.servlet.context.MyServletWebServerApplicationContext;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 10:02
 * Version:V1.0
 */
public class TestSpringApplication {
    public static void main(String[] args) {
//        testIOC();
        MyApplicationContext context = new MyServletWebServerApplicationContext("classpath:application.properties");
        try {
            Object object = context.getBean(DemoController.class);
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试IOC
     */
    private static void testIOC() {
        MyApplicationContext context = new MyApplicationContext("classpath:application.properties");
        try {
            Object object = context.getBean(DemoController.class);
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
