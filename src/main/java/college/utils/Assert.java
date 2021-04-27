package college.utils;

/**
 * @author: xuxianbei
 * Date: 2021/4/19
 * Time: 15:36
 * Version:V1.0
 */
public class Assert {

    public static void isInstanceOf(Class<?> type, Object obj) {
        notNull(type, "Type to check against must not be null");

    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");

    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
