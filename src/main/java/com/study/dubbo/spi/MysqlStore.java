package com.study.dubbo.spi;

@Strategy("mysql")
public class MysqlStore implements Store{

	@Override
	public String add(String data) {
		return "mysql:" + data;
	}

}
