package college.simple.spring.formework.orm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 21:35
 * Version:V1.0
 */
public class ClassMappings {

    public static Map<String, Method> findPublicGetters(Class<?> clazz) {
        return null;
    }

    public static Map<String, Method> findPublicSetters(Class<?> clazz) {
        return null;
    }

    public static <T> Field[] findFields(Class<T> entityClass) {
        return entityClass.getDeclaredFields();
    }
}
