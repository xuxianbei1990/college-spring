package college.beans.factory.support;

import college.context.BeansException;

/**
 * @author: xuxianbei
 * Date: 2021/4/27
 * Time: 11:04
 * Version:V1.0
 */
@FunctionalInterface
public interface ObjectFactory<T> {

    /**
     * Return an instance (possibly shared or independent)
     * of the object managed by this factory.
     * @return the resulting instance
     * @throws BeansException in case of creation errors
     */
    T getObject() throws BeansException;

}