package college.simple.spring.formework.orm;

import college.simple.spring.javax.core.common.jdbc.BaseDao;
import college.simple.spring.javax.core.common.utils.GenericsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 19:52
 * Version:V1.0
 */
@Slf4j
public abstract class BaseDaoSupport<T extends Serializable, PK extends Serializable> implements BaseDao {

    private EntityOperation<T> op;

    private String tableName = "";

    private DataSource dataSourceReadOnly;
    private DataSource dataSourceWrite;

    private JdbcTemplate jdbcTemplateReadOnly;
    private JdbcTemplate jdbcTemplateWrite;

    protected BaseDaoSupport() {
        try {
            Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass(), 0);
            op = new EntityOperation<T>(entityClass, this.getPKColumn());
            this.setTableName(op.tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract String getPKColumn();

    protected void setTableName(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            this.tableName = op.tableName;
        } else {
            this.tableName = tableName;
        }
    }

    public List<T> select(QueryRule queryRule) throws Exception {
        QueryRuleSqlBuilder bulider = new QueryRuleSqlBuilder(queryRule);
        String ws = removeFirstAnd(bulider.getWhereSql());
        String whereSql = ("".equals(ws) ? ws : (" where " + ws));
        String sql = "select " + op.allColumn + " from " + getTableName() + whereSql;
        Object[] values = bulider.getValues();
        String orderSql = bulider.getOrderSql();
        orderSql = (StringUtils.isEmpty(orderSql) ? " " : (" order by " + orderSql));
        sql += orderSql;
        log.debug(sql);
        return this.jdbcTemplateReadOnly().query(sql, this.op.rowMapper, values);
    }

    protected String getTableName() {
        return tableName;
    }

    private String removeFirstAnd(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return sql;
        }
        return sql.trim().toLowerCase().replaceAll("^\\s*and", "") + " ";
    }

    private JdbcTemplate jdbcTemplateReadOnly() {
        return this.jdbcTemplateReadOnly;
    }

    protected void setDataSourceReadOnly(DataSource dataSourceReadOnly) {
        this.dataSourceReadOnly = dataSourceReadOnly;
        jdbcTemplateReadOnly = new JdbcTemplate(dataSourceReadOnly);
    }

    protected void setDataSourceWrite(DataSource dataSource) {
        this.dataSourceWrite = dataSource;
        jdbcTemplateWrite = new JdbcTemplate(dataSource);
    }
}
