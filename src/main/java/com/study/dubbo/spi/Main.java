package com.study.dubbo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

/**
 * 当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里
 * 同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现类。
 * 而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名， 并装载实例化，完成模块的注入。
 * 基于这样一个约定就能很好的找到服务接口的实现类，而不需要再代码里制定。 jdk提供服务实现查找的一个工具类：java.util.ServiceLoader
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
			// 当调用getClass()时，返回这个对象真实的Class对象
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
