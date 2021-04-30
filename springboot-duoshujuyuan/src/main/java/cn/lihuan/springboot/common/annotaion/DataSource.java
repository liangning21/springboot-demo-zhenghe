package cn.lihuan.springboot.common.annotaion;

import cn.lihuan.springboot.common.enums.DataSourcesType;

import java.lang.annotation.*;

/**
 * 数据源自定义注解
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {

    DataSourcesType name() default DataSourcesType.MASTER;

}