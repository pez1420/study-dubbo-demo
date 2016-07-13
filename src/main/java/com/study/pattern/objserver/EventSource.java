package com.study.pattern.objserver;

import java.util.Enumeration;
import java.util.Vector;

/** ��ɫ: �¼�Դ(���۲���)
 * 
 */
public class EventSource {
	
	private Vector<IMessageListener> container = new Vector<IMessageListener>();
	
	/**���¼�Դע��ʱ�������
	 * 
	 * @param eventListener �¼�������(���Ľ�ɫ�ǹ۲���)
	 * @return �Ƿ�ע��ɹ�
	 */
	public boolean addListener(IMessageListener eventListener) {
		return container.add(eventListener);
	}
	
	/** ��container���Ƴ�������
	 * 
	 * @param eventListener �¼�������(���Ľ�ɫ�ǹ۲���)
	 * @return ��������ü����������Ƴ�֮�󷵻�true
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
