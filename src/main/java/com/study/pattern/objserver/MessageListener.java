package com.study.pattern.objserver;

import java.util.concurrent.atomic.AtomicInteger;

/** ÊÂ¼ş¼àÌıÆ÷
 * 
 */
public class MessageListener extends IMessageListener {

	private static final AtomicInteger seq = new AtomicInteger(0);
	
	public MessageListener(String name) {
		super(name);
	}

	@Override
	public void handleEvent(MessageEvent event) {
		System.out.print(name);
		event.say(seq.incrementAndGet());
	}
	
}
