package com.coding.oj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.coding.oj.mapper")
public class MybatisPlusConfig {

}