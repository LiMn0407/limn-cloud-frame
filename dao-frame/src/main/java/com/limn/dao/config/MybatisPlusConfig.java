package com.limn.dao.config;


import com.limn.dao.db.properties.DynamicDataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Order(Integer.MIN_VALUE)
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class MybatisPlusConfig {
}
