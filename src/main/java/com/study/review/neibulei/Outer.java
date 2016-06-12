package com.study.review.neibulei;

public class Outer {
    public class Inner { 
    	public static final int value = 123;
    	
        public void print(String str) { 
            System.out.println(str); 
        } 
    } 
    
    
	public static void main(String[] args) {
		Outer.Inner inner = new Outer().new Inner();
		inner.print("123333");

	}

}
