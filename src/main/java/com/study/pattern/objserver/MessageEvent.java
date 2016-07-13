package com.study.pattern.objserver;

import java.util.EventObject;

public class MessageEvent extends EventObject {

	private static final long serialVersionUID = -2497504357444585700L;

	public MessageEvent(Object source) {
		super(source);
	}
	
	public void say(int i) {
		System.out.println(" i=" + i);
	}

}
