package com.study.pattern.objserver;

import java.util.EventListener;

public abstract class IMessageListener implements EventListener {

	protected String name;
	
	public IMessageListener(String name) {
		super();
		this.name = name;
	}
	
	public abstract void handleEvent(MessageEvent event);
}
