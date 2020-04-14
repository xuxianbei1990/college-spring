package college.simple.spring;

import college.simple.spring.demo.controller.DemoController;
import college.simple.spring.formework.context.MyApplicationContext;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 10:02
 * Version:V1.0
 */
public class TestSpringApplication {
    public static void main(String[] args) {
        MyApplicationContext context = new MyApplicationContext("classpath:application.properties");
        try {
            Object object = context.getBean(DemoController.class);
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
