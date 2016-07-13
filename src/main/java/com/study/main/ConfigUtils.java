package com.study.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigUtils {

	private static volatile Properties properties;
	
	public static Properties getProperties() {
		if (properties == null) {
			synchronized(ConfigUtils.class) {
				if (properties == null) {
					String path = System.getenv("qq");
					if (path == null || path.isEmpty()) {
						properties = ConfigUtils.loadProperties();
					} else {
						properties = ConfigUtils.loadProperties(path);
					}
				}
			}
		}
		return properties;
	}
	
	
	/**默认从ClassPath加载属性文件
	 * 
	 * @return
	 */
	public static Properties loadProperties() {
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = ConfigUtils.class.getClassLoader().getResourceAsStream("log4j.properties");
			System.out.println("线程" + Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getContextClassLoader() == ConfigUtils.class.getClassLoader());
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	public static Properties loadProperties(String path) {
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			properties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	
	public static String getProperty(String key) {
		Properties properties = getProperties();
		return properties.getProperty(key);
	}
	public static void main(String[] args) {
		
		for (int i = 0 ; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Properties properties = ConfigUtils.getProperties();
					if (properties != null) {
						Enumeration<?> enu = properties.propertyNames();
						while (enu.hasMoreElements()) {
							String key = (String)enu.nextElement();
							System.out.println(key + "=" + ConfigUtils.getProperty(key));
						}
					}
				}
			}, "thread" + (i+1)).start();
			
			System.out.println(i+1);
		}
	}
	
}
