package com.study.dubbo.spi;

@SPI("redis")
public interface Store {

	String add(String data);
	
}
