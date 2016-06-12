package com.study.review.neibulei;

public class OuterOne {

	public static void main(String[] args) {
		OuterOne out = new OuterOne();
		Object obj = out.method();
		System.out.println(obj.getClass());
	}

	Object method() {
		final int locvar = 1;
		class Inner {
			void displayLocvar() {
				System.out.println("locvar = " + locvar);
			}
		}
		Object in = new Inner();
		return in;
	}

}
