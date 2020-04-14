package college.simple.spring.formework.webmvc.servlet;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author: xuxianbei
 * Date: 2020/4/14
 * Time: 17:22
 * Version:V1.0
 */
@Data
public class MyHandlerMapping {
    private Object controller;	//保存方法对应的实例
    private Method method;		//保存映射的方法
    private Pattern pattern;    //URL的正则匹配

    public MyHandlerMapping(Pattern pattern, Object controller, Method method) {

    }
}
