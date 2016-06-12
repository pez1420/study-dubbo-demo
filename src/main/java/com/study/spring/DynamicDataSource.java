package com.study.spring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

//·������Դ
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }

}


