package com.study.pattern.objserver;

import java.util.Enumeration;
import java.util.Vector;

/** 角色: 事件源(被观察者)
 * 
 */
public class EventSource {
	
	private Vector<IMessageListener> container = new Vector<IMessageListener>();
	
	/**向事件源注册时间监听器
	 * 
	 * @param eventListener 事件监听器(它的角色是观察者)
	 * @return 是否注册成功
	 */
	public boolean addListener(IMessageListener eventListener) {
		return container.add(eventListener);
	}
	
	/** 从container中移出监听器
	 * 
	 * @param eventListener 事件监听器(它的角色是观察者)
	 * @return 如果包含该监听器，则移出之后返回true
	 */
	public boolean removeListener(IMessageListener eventListener) {
		return container.remove(eventListener);
	}
	
	public void notifyEventToListener() {
		Enumeration<IMessageListener> listeners = container.elements();
		while (listeners.hasMoreElements()) {
			IMessageListener listener = listeners.nextElement();
			listener.handleEvent(new MessageEvent(this));
		}
	}
	
}
