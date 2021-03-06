package cn.lihuan.springboot.druid;

import cn.lihuan.springboot.common.enums.DataSourcesType;
import cn.lihuan.springboot.core.datasource.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 主库
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.setDataSource(DruidDataSourceBuilder.create().build());
    }


    /**
     * 从库
     */
    @Bean
    @ConditionalOnProperty( prefix = "spring.datasource.druid.slave", name = "enable", havingValue = "true")//是否开启数据源开关---若不开启 默认适用默认数据源
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.setDataSource(DruidDataSourceBuilder.create().build());
    }

    /**
     * 设置数据源
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        //添加数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourcesType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourcesType.SLAVE.name(), slaveDataSource);

        DynamicDataSource dynamicDataSource = DynamicDataSource.build();
        //默认数据源配置 DefaultTargetDataSource
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        //额外数据源配置 TargetDataSources
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }

}