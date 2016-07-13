package com.study.grammar;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
