package com.study.zk;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZKTest {
	private ZkClient zk;

	private String nodeName = "/myApp";

	@Before
	public void initZkClient() {
		//zk = new ZkClient("localhost:2181,localhost:2182,localhost:2183");
		zk = new ZkClient("127.0.0.1:2181");
	}

	@After
	public void close() {
		zk.close();
		System.out.println("zkclient close");
	}

	@Test
	public void testListener() {
		zk.subscribeDataChanges(nodeName, new IZkDataListener() {

			@Override
			public void handleDataDeleted(String s) throws Exception {
				System.out.println("node data deleted");
				System.out.println("node=>" + s);

			}

			@Override
			public void handleDataChange(String s, Object o) throws Exception {
				System.out.println("node data changed");
				System.out.println("node=>" + s);
				System.out.println("data=>" + o);
			}
		});

		System.out.println("we have ready!");

		try {
			synchronized (ZKTest.class) {
				ZKTest.class.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateConfig() {
		if (!zk.exists(nodeName)) {
			zk.createPersistent(nodeName);
		}
		
		zk.writeData(nodeName, 1);
		zk.writeData(nodeName, 2);
		
		zk.delete(nodeName);
		zk.delete(nodeName); //删除一个不存在的几点并不会报错
		
	}

}