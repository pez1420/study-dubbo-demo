package com.study.dubbo.grammar;

public class StringTest {

	public static void test1() {
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2);
	}
	
	
	public static void test2() {
		String s1 = new String("abc");
		String s2 = new String("abc");
		System.out.println(s1 == s2);
	}
	
	public static void test3() {
		String s1 = "abc";
		String s2 = "a";
		String s3 = "bc";
		String s4 = s2 + s3;
		System.out.println(s1 == s4);
	}
	
	public static void test4() {
		String s1 = "abc";
		String s2 = "a" + "bc";
		System.out.println(s1 == s2);
	}
	
	public static void main(String[] args) {
        test1();
        test2();
        test3();
		test4();
	}

}
