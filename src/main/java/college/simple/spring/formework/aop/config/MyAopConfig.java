package college.simple.spring.formework.aop.config;

import lombok.Data;

/**
 * @author: xuxianbei
 * Date: 2020/4/16
 * Time: 10:18
 * Version:V1.0
 */
@Data
public class MyAopConfig {
    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;
}
