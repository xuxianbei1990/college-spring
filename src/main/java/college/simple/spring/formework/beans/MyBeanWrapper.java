package college.simple.spring.formework.beans;

import lombok.Data;

/**
 * @author: xuxianbei
 * Date: 2020/4/13
 * Time: 15:18
 * Version:V1.0
 */
@Data
public class MyBeanWrapper {

    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public MyBeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
        this.wrappedClass = wrappedInstance.getClass();
    }
}
