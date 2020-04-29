package college.simple.spring.demo.dao;

import college.simple.spring.demo.entity.Member;
import college.simple.spring.formework.orm.BaseDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 19:51
 * Version:V1.0
 */
@Repository
public class MemberDao extends BaseDaoSupport<Member, Long> {

    @Override
    protected String getPKColumn() {
        return "id";
    }

    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource){
        super.setDataSourceReadOnly(dataSource);
        super.setDataSourceWrite(dataSource);
    }
}
