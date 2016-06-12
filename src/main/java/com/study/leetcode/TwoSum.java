package com.study.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, find two numbers such that they add up to 
 * a specific target number.
 * The function twoSum should return indices of the two numbers 
 * such that they add up to the target, where index1 must be less than index2. 
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 * 第一种 o(n^2) 暴力比对
 * 第二种(nlogn),先排序，再用两个指针
 * 第三种
 *
 */
public class TwoSum {
	/**
	 * O(n)。哈希表。将每个数字放在map中，历遍数组，如果出现和数组中的某一个值
	 * 相加为target的时候，break。这个方法同样适用于多组解的情况。
	 * 
	 * 
	 * 思路是循环一次，每次都判断当前数组索引位置的值在不在hashtable里，
	 * 不在的话，加入进去，key为数值，value为它的索引值；在的话取得他的key记为n（此时n一定小于循环变量i），
	 * 接下来再在hashtable中查找（target-当前数值）这个数，利用了hashtable中查找元素时间为常数的优势，
	 * 如果找到了就结束，此处需要注意的是，如果数组中有重复的值出现，
	 * 那么第二次出现时就不会加入到hashtable里了，
	 * 比如3,4,3,6；target=6时，当循环到第二个3时，也可以得到正确结果。
	 */
	public int[] twoSum(int []num, int target) {
		int []result = new int[2];
		HashMap<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < num.length; i++) {
			Integer n = map.get(num[i]);
			if (n == null) map.put(num[i], i);
			n = map.get(target - num[i]);
			if (n != null && n < i) {
				result[0] = n + 1;
				result[1] = i + 1;
				return result;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a = {55, 3};
		Arrays.sort(a);
		System.out.println(a.toString());
	}

}
