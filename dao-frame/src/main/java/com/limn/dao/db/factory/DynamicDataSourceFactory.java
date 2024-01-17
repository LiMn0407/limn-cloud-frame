package com.limn.dao.db.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.limn.dao.db.properties.DynamicDataSourceConfig;

import java.sql.SQLException;

public class DynamicDataSourceFactory {


    public static DruidDataSource buildDynamicDataSource(String dataSourceName, DynamicDataSourceConfig config){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(config.getDriverClassName());
        druidDataSource.setUrl(config.getUrl());
        druidDataSource.setUsername(config.getUsername());
        druidDataSource.setPassword(config.getPassword());

        druidDataSource.setInitialSize(config.getInitialSize());
        druidDataSource.setMaxActive(config.getMaxActive());
        druidDataSource.setMinIdle(config.getMinIdle());
        druidDataSource.setMaxWait(config.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxEvictableIdleTimeMillis(config.getMaxEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(config.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(config.getValidationQueryTimeout());
        druidDataSource.setTestOnBorrow(config.isTestOnBorrow());
        druidDataSource.setTestOnReturn(config.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(config.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(config.getMaxOpenPreparedStatements());
        druidDataSource.setSharePreparedStatements(config.isSharePreparedStatements());
        druidDataSource.setName(dataSourceName);
        try {
            druidDataSource.setFilters(config.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;

    }


}
