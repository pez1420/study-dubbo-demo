package com.study.dubbo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

/**
 * ��������ṩ�ߣ��ṩ�˷���ӿڵ�һ��ʵ��֮����jar����META-INF/services/Ŀ¼��
 * ͬʱ����һ���Է���ӿ��������ļ������ļ������ʵ�ָ÷���ӿڵľ���ʵ���ࡣ
 * �����ⲿ����װ�����ģ���ʱ�򣬾���ͨ����jar��META-INF/services/��������ļ��ҵ������ʵ�������� ��װ��ʵ���������ģ���ע�롣
 * ��������һ��Լ�����ܺܺõ��ҵ�����ӿڵ�ʵ���࣬������Ҫ�ٴ������ƶ��� jdk�ṩ����ʵ�ֲ��ҵ�һ�������ࣺjava.util.ServiceLoader
 * 
 * @author Administrator
 *
 */
public class Main<T> {

	private ConcurrentHashMap<String, T> cacheInstances = new ConcurrentHashMap<String, T>();

	public void impl() {
		ServiceLoader<Store> loader = ServiceLoader.load(Store.class);
		Iterator<Store> iter = loader.iterator();
		while (iter.hasNext()) {
			Store store = iter.next();
			Class<?> clazz = store.getClass();
			System.out.println(clazz.getName());
			// System.out.println(store.add("data"));
		}
	}

	public  void load(Class<T> type) {
		ServiceLoader<T> loader = ServiceLoader.load(type);
		Iterator<T> iter = loader.iterator();
		while (iter.hasNext()) {
			T t = iter.next();
			// ������getClass()ʱ���������������ʵ��Class����
			Class<?> clazz = t.getClass();

			if (!type.isAssignableFrom(clazz)) {
				throw new IllegalStateException("class " + clazz.getName() + "is not a subtype of " + type.getName());
			}

			// String name = type.getAnnotation(SPI.class).value();
			// if (!StringUtils.isEmpty(name)) {
			// System.out.println(name);
			// } else {
			// throw new IllegalStateException("Spi annotation haven't been
			// seted value of " + type.getName());
			// }

			Strategy strategy = clazz.getAnnotation(Strategy.class);
			if (strategy == null) {
				throw new IllegalStateException(
						"Strategy annotation haven't been seted of  " + clazz.getCanonicalName());
			}

			String strategyName = strategy.value();
			if (StringUtils.isEmpty(strategyName))
				throw new IllegalStateException(
						"StrategyVlue " + clazz.getCanonicalName() + "is not allowed to be null!");
			
			cacheInstances.put(strategyName, t);
			
			// System.out.println(type.getName() + ": " + clazz.getName());
			// System.out.println(store.add("data"));
			
		}

	}

	public static void main(String[] args) {
		Main<Store> main = new Main<Store>();
		main.load(Store.class);
		
	}
}
