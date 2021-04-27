package college.utils;

/**
 * @author: xuxianbei
 * Date: 2021/4/20
 * Time: 14:12
 * Version:V1.0
 */
public class ObjectUtils {

    private static final String EMPTY_STRING = "";

    public static String identityToString(Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        String className = obj.getClass().getName();
        String identityHexString = getIdentityHexString(obj);
        return className + '@' + identityHexString;
    }

    public static String getIdentityHexString(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }
}


