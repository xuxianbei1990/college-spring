package college.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xuxianbei
 * Date: 2021/4/26
 * Time: 13:58
 * Version:V1.0
 */
public class DefaultSingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
//        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
//            synchronized (this.singletonObjects) {
//                singletonObject = this.earlySingletonObjects.get(beanName);
//                if (singletonObject == null && allowEarlyReference) {
//                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
//                    if (singletonFactory != null) {
//                        singletonObject = singletonFactory.getObject();
//                        this.earlySingletonObjects.put(beanName, singletonObject);
//                        this.singletonFactories.remove(beanName);
//                    }
//                }
//            }
//        }
        return singletonObject;
    }

    public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
        //说明一个问题：singletonObjects 只能保证put是线程安全的
        synchronized (this.singletonObjects) {
            Object singletonObject = this.singletonObjects.get(beanName);
            if (singletonObject == null) {
                //Spring在这里之前还做很多校验
                singletonObject = singletonFactory.getObject();
            }
        }
        return null;
    }
}
