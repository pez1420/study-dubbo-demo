package com.study.dubbo.spi;

@Strategy("redis")
public class RedisStore implements Store{

	@Override
	public String add(String data) {
		return "redis:" + data;
	}

}
