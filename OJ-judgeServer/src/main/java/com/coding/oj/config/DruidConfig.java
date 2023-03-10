package com.coding.oj.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Data
public class DruidConfig {

    /*
     * 绑定全局配置文件中的 druid 数据源属性到com.alibaba.druid.pool.DruidDataSource从而让它们生效
     * @ConfigurationProperties(prefix = "spring.datasource"): 作用就是将全局配置文件中前缀为spring.datasource的属性值注入到com.alibaba.druid.pool.DruidDataSource 的同名参数中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

}
