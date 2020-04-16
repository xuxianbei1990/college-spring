package college.simple.spring.formework.web.servlet;

import college.simple.spring.formework.annotation.MyRequestParam;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xuxianbei
 * Date: 2020/4/14
 * Time: 17:23
 * Version:V1.0
 */
public class MyHandlerAdapter {
    public boolean supports(Object handler) {
        return (handler instanceof MyHandlerMapping);
    }

    public Object handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        MyHandlerMapping handlerMapping = (MyHandlerMapping) handler;

        //方法的参数名称和下标
        Map<String, Integer> paramIndexMapping = new HashMap<>();
        Annotation[][] annotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation1 : annotations[i]) {
                if (annotation1 instanceof MyRequestParam) {
                    String paramName = ((MyRequestParam) annotation1).value();
                    if (!StringUtils.isEmpty(paramName.trim())) {
                        paramIndexMapping.putIfAbsent(paramName, i);
                    }
                }
            }
        }
        //提取方法中的request和response参数
        Class<?>[] paramsTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramsTypes.length; i++) {
            Class<?> type = paramsTypes[i];
            if (type == HttpServletRequest.class ||
                    type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }
        //获得方法的形参列表
        Map<String, String[]> params = req.getParameterMap();

        //实参列表
        Object[] paramValues = new Object[paramsTypes.length];

        for (Map.Entry<String, String[]> parm : params.entrySet()) {
            String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", ",");

            if (!paramIndexMapping.containsKey(parm.getKey())) {
                continue;
            }

            int index = paramIndexMapping.get(parm.getKey());
            paramValues[index] = caseStringValue(value, paramsTypes[index]);
        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object result = null;
        try {
            result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (result == null || result instanceof Void) {
            return null;
        }
        return result;
    }

    private Object caseStringValue(String value, Class<?> paramsType) {
        if (String.class == paramsType) {
            return value;
        }
        //如果是int
        if (Integer.class == paramsType) {
            return Integer.valueOf(value);
        } else if (Double.class == paramsType) {
            return Double.valueOf(value);
        } else {
            if (value != null) {
                return value;
            }
            return null;
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现，希望小伙伴自己来实现

    }
}
