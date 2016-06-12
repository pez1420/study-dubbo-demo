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
 * ��һ�� o(n^2) �����ȶ�
 * �ڶ���(nlogn),��������������ָ��
 * ������
 *
 */
public class TwoSum {
	/**
	 * O(n)����ϣ����ÿ�����ַ���map�У��������飬������ֺ������е�ĳһ��ֵ
	 * ���Ϊtarget��ʱ��break���������ͬ�������ڶ����������
	 * 
	 * 
	 * ˼·��ѭ��һ�Σ�ÿ�ζ��жϵ�ǰ��������λ�õ�ֵ�ڲ���hashtable�
	 * ���ڵĻ��������ȥ��keyΪ��ֵ��valueΪ��������ֵ���ڵĻ�ȡ������key��Ϊn����ʱnһ��С��ѭ������i����
	 * ����������hashtable�в��ң�target-��ǰ��ֵ���������������hashtable�в���Ԫ��ʱ��Ϊ���������ƣ�
	 * ����ҵ��˾ͽ������˴���Ҫע����ǣ�������������ظ���ֵ���֣�
	 * ��ô�ڶ��γ���ʱ�Ͳ�����뵽hashtable���ˣ�
	 * ����3,4,3,6��target=6ʱ����ѭ�����ڶ���3ʱ��Ҳ���Եõ���ȷ�����
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
