package com.study.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class SetConfig {

	public static final String CLIENT_PORT = "2181";
	public static final String root = "/myconf";
	public static final String UrlNode = root + "/url";
	public static final String UserNameNode = root + "/username";
	public static final String PasswdNode = root + "/passwd";

	public static final String auth_type = "digest";
	public static final String auth_passwd = "password";

	public static void main(String[] args) {
		// 创建一个与服务器的连接
		try {
			ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT, 50000, new Watcher() {
				// 监控所有被触发的事件
				public void process(WatchedEvent event) {
					System.out.println("已经触发了" + event.getType() + "事件！");
				}
			});

			while (ZooKeeper.States.CONNECTED != zk.getState()) {
				Thread.sleep(3000);
			}

			zk.addAuthInfo(auth_type, auth_passwd.getBytes());

			if (zk.exists(root, true) == null) {
				zk.create(root, "root".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			}

			if (zk.exists(UrlNode, true) == null) {
				zk.create(UrlNode, "10.0.0.120:3306".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			}

			if (zk.exists(UserNameNode, true) == null) {
				zk.create(UserNameNode, "root".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			}

			if (zk.exists(PasswdNode, true) == null) {
				zk.create(PasswdNode, "root".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			}

			zk.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		
	}

}
