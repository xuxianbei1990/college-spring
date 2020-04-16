package college.simple.spring.formework.web.servlet;

import college.simple.spring.formework.annotation.MyController;
import college.simple.spring.formework.annotation.MyRequestMapping;
import college.simple.spring.formework.context.MyApplicationContext;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: xuxianbei
 * Date: 2020/4/14
 * Time: 17:20
 * Version:V1.0
 */
@Slf4j
public class MyDispatcherServlet extends HttpServlet {
    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private MyApplicationContext context;

    private List<MyHandlerMapping> handlerMappingList = new ArrayList<>();

    private Map<MyHandlerMapping, MyHandlerAdapter> handlerAdapterMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        MyHandlerMapping handler = getHandler(req);

        if (handler == null) {
            processDispatchResult(req, resp, null);
            return;
        }

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        Object o = handlerAdapter.handle(req, resp, handler);
        processDispatchResult(req, resp, o);
    }

    private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handler) {
        if (handlerAdapterMap.isEmpty()) {
            return null;
        }
        MyHandlerAdapter handlerAdapter = handlerAdapterMap.get(handler);
        if (handlerAdapter.supports(handler)) {
            return handlerAdapter;
        }
        return null;
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, Object o) {
        try {
            resp.getOutputStream().write(JSONObject.toJSONString(o).getBytes());
            resp.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MyHandlerMapping getHandler(HttpServletRequest req) {
        if (handlerMappingList.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        for (MyHandlerMapping handler : this.handlerMappingList) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = new MyApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        initStrategies(context);
    }

    private void initStrategies(MyApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化模板处理器
        initThemeResolver(context);
        //handlerMapping，必须实现
        initHandlerMappings(context);

        //初始化参数适配器，必须实现
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);

    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {

    }

    private void initHandlerAdapters(MyApplicationContext context) {
        handlerMappingList.stream().forEach(myHandlerMapping -> {
            handlerAdapterMap.putIfAbsent(myHandlerMapping, new MyHandlerAdapter());
        });
    }

    private void initHandlerMappings(MyApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();
                if (!clazz.isAnnotationPresent(MyController.class)) {
                    continue;
                }
                String baseUrl = "";
                if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    baseUrl = requestMapping.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                        continue;
                    }
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    handlerMappingList.add(new MyHandlerMapping(pattern, controller, method));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initThemeResolver(MyApplicationContext context) {

    }

    private void initLocaleResolver(MyApplicationContext context) {

    }

    private void initMultipartResolver(MyApplicationContext context) {

    }
}
