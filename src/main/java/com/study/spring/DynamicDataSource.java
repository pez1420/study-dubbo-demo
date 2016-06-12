package com.study.spring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

//路由数据源
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }

}


