package college.simple.spring.formework.orm;

import org.hibernate.persister.entity.PropertyMapping;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 20:13
 * Version:V1.0
 */
public class EntityOperation<T> {
    public String allColumn = "*";
    public final RowMapper<T> rowMapper;

    public final Map<String, PropertyMapping> mappings;
    public Class<T> entityClass = null;

    public final String tableName;

    public EntityOperation(Class<T> clazz,String pk) throws Exception{
        this.rowMapper = createRowMapper();
        this.entityClass = clazz;
        Table table = entityClass.getAnnotation(Table.class);
        if (table != null) {
            this.tableName = table.name();
        } else {
            this.tableName =  entityClass.getSimpleName();
        }
        Map<String, Method> getters = ClassMappings.findPublicGetters(entityClass);
        Map<String, Method> setters = ClassMappings.findPublicSetters(entityClass);
        Field[] fields = ClassMappings.findFields(entityClass);
        this.mappings = getPropertyMappings(getters, setters, fields);
    }

    Map<String, PropertyMapping> getPropertyMappings(Map<String, Method> getters, Map<String, Method> setters, Field[] fields) {
        Map<String, PropertyMapping> mappings = new HashMap<String, PropertyMapping>();
        String name;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Transient.class))
                continue;
            name = field.getName();
            if(name.startsWith("is")){
                name = name.substring(2);
            }
            name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
            Method setter = setters.get(name);
            Method getter = getters.get(name);
            if (setter == null || getter == null){
                continue;
            }
            Column column = field.getAnnotation(Column.class);
//            if (column == null) {
//                mappings.put(field.getName(), new PropertyMapping(getter, setter, field));
//            } else {
//                mappings.put(column.name(), new PropertyMapping(getter, setter, field));
//            }
        }
        return mappings;
    }

    RowMapper<T> createRowMapper() {
        return (rs, rowNum) -> {
            try {
                T t = entityClass.newInstance();
                ResultSetMetaData meta = rs.getMetaData();
                int columns = meta.getColumnCount();
                String columnName;
                for (int i = 1; i <= columns; i++) {
                    Object value = rs.getObject(i);
                    columnName = meta.getColumnName(i);
                    fillBeanFieldValue(t,columnName,value);
                }
                return t;
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    protected void fillBeanFieldValue(T t, String columnName, Object value) {
        if (value != null) {
            PropertyMapping pm = mappings.get(columnName);
            if (pm != null) {
                try {
//                    pm.set(t, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
