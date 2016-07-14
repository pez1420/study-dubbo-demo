package com.study.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Assert;
import org.junit.Test;

public class CollectionUtils {

	public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
		Set<E> s = new HashSet<E>(s1);
		s.addAll(s2);
		return s;
	}

	public static List<Integer> getEmptyIntegers() {
		return Collections.emptyList();
	}

	@Test
	public void emptyList() {
		List<Integer> ints = getEmptyIntegers();
		Assert.assertTrue(ints != null && ints.size() == 0);
	}

	@Test
	public void consumeForConditon() {
		StringBuilder idsBuilder = new StringBuilder();
		for (int i = 0; i < 1000000; i++)
			idsBuilder.append(i).append(",");
		String ids = idsBuilder.toString();

		long start = System.currentTimeMillis();
		for (String id : ids.split(",")) {
		}
		long timeOne = System.currentTimeMillis() - start;

		String[] splitIds = ids.split(",");
		start = System.currentTimeMillis();
		for (String id : splitIds) {
		}
		long timeTwo = System.currentTimeMillis() - start;
		Assert.assertTrue("timeOne > timeTwo", timeOne > timeTwo);
	}

	@Test
	public void clearMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "value");
		map.clear();
	}

	@Test
	public void testIterator() {
		List<Integer> customerIdList = new ArrayList<>();
		customerIdList.add(1);
		customerIdList.add(2);
		customerIdList.add(3);
		List<Integer> initedCustomerIdList = new ArrayList<>();

		initedCustomerIdList.add(1);
		initedCustomerIdList.add(2);
		for (Integer id : initedCustomerIdList)
			customerIdList.remove(id);
		System.out.println(customerIdList.toString());

		Iterator<Integer> it = initedCustomerIdList.iterator();

	}

	@Test
	public void containList() {
		List<String> list = new ArrayList<String>();
		list.add("a"); // 向列表中添加数据
		list.add("b"); // 向列表中添加数据
		list.add("c"); // 向列表中添加数据
		String o = "d";
		System.out.println("list对象中是否包含元素" + o + ":" + list.contains(o));
		
		List<Integer> customerIdList = new ArrayList<>();
		customerIdList.add(1);
		customerIdList.add(2);
		customerIdList.add(3);
		
		List<Integer> tmpList = new ArrayList<>();
		tmpList.add(1);
		tmpList.add(2);
		for (Integer tmp : tmpList) {
			customerIdList.remove(tmp);
		}
		
		System.out.println(tmpList);
		System.out.println(customerIdList);
		
	}
}
