package college.simple.spring.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 19:54
 * Version:V1.0
 */
@Entity
@Table(name="t_member")
@Data
public class Member implements Serializable {
    @Id
    private Long id;
    private String name;
    private String addr;
    private Integer age;
}
