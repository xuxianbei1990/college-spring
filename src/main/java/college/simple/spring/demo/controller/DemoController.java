package college.simple.spring.demo.controller;

import college.simple.spring.demo.service.DemoService;
import college.simple.spring.formework.annotation.MyAutowired;
import college.simple.spring.formework.annotation.MyController;
import college.simple.spring.formework.annotation.MyRequestMapping;

/**
 * @author: xuxianbei
 * Date: 2020/4/9
 * Time: 9:55
 * Version:V1.0
 */
@MyController
@MyRequestMapping("/web")
public class DemoController {

    @MyAutowired
    DemoService demoService;

    @MyRequestMapping("/hello")
    public String hello() {
        return demoService.hello();
    }

}
