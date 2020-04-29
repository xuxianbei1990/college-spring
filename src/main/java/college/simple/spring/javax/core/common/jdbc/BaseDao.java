package college.simple.spring.javax.core.common.jdbc;

import college.simple.spring.formework.orm.QueryRule;

import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 20:08
 * Version:V1.0
 */
public interface BaseDao<T,PK> {

    /**
     * 获取列表
     * @param queryRule 查询条件
     * @return
     */
    List<T> select(QueryRule queryRule) throws Exception;
}
