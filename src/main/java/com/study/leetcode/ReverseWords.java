package com.study.leetcode;

/**
 * Given an input string, reverse the string word by word.

For example, given s = "the sky is blue", return "blue is sky the".

Java Solution

This problem is pretty straightforward. We first split the string to words array, 
and then iterate through the array and add each element to a new string. 
Note: StringBuilder should be used to avoid creating too many Strings. 
If the string is very long, using String is not scalable 
since String is immutable and too many objects will be created and garbage collected.
 * @author Administrator
 *
 */
public class ReverseWords {
	public static String  reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
 
		// split to words by space
		String[] arr = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; --i) {
			if (!arr[i].equals("")) {
				sb.append(arr[i]).append(" ");
			}
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}
	
	public static void main(String[] args) {
		String s = "a   b c";
		String[] arr = s.split(" ");
		System.out.println(arr.toString());
		//String d = reverseWords(s);
		//System.out.println(d);
	}
}
