package com.study.review.alibaba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/***
 * TreeMap数据结构如何进行表示? 当value有多个相同
 * 
 * @author Administrator
 *
 */

class ValueComparaotr implements Comparator<String> {
	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	public ValueComparaotr(HashMap<String, Integer> counter) {
		map.putAll(counter);
	}

	@Override
	public int compare(String s1, String s2) {
		int d = map.get(s2) - map.get(s1);
		if (d == 0)
			return 1;
		else
			return d;
	}
}

public class SortFile {

	public TreeMap<String, Integer> sortLog(String path) {
		if (path == null)
			throw new IllegalArgumentException("path is not allowed to be null!");

		BufferedReader bufferedReader = null;
		FileReader filReader = null;
		try {
			filReader = new FileReader(path);
			bufferedReader = new BufferedReader(filReader);
			HashMap<String, Integer> logs = new HashMap<String, Integer>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				if (line.length() >= 3 && "aaa".equals(line.substring(0, 3))) {
					Integer count = logs.get(line);
					if (count != null) {
						logs.put(line, count + 1);
					} else {
						logs.put(line, 1);
					}
				}
			}

			TreeMap<String, Integer> sortLogs = new TreeMap<String, Integer>(new ValueComparaotr(logs));
			sortLogs.putAll(logs);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (filReader != null)
					filReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		SortFile sortFile = new SortFile();
		TreeMap<String, Integer> sortLogs = sortFile.sortLog("D:\\worklog.log");
		if (sortLogs != null && !sortLogs.isEmpty()) {
			for (Map.Entry<String, Integer> entry : sortLogs.entrySet()) {
				System.out.println(entry.getValue() + ":" + entry.getKey());
			}
		}
	}
}
