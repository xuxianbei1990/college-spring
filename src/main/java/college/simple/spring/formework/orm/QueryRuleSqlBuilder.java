package college.simple.spring.formework.orm;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 20:11
 * Version:V1.0
 */
public class QueryRuleSqlBuilder {

    private String whereSql = "";
    private Object[] valueArr = new Object[]{};
    private String orderSql = "";

    public QueryRuleSqlBuilder(QueryRule queryRule) {

    }

    public String getWhereSql() {
        return this.whereSql;
    }

    public Object[] getValues() {
        return this.valueArr;
    }

    public String getOrderSql() {
        return this.orderSql;
    }
}
