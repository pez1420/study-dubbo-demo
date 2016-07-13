package com.study.pattern.objserver;

public class Manager {

	public static void main(String[] args) {
		EventSource source = new EventSource();
		MessageListener listener1 = new MessageListener("������-1");
		MessageListener listener2 = new MessageListener("������-2");
		
		source.addListener(listener1);
		source.addListener(listener2);
		
		final String lname = "������-3";
		source.addListener(new IMessageListener(lname) {
			@Override
			public void handleEvent(MessageEvent event) {
				//EventSource source = (EventSource) event.getSource();
				System.out.println(name + " " + event.toString());
			}
		});
		
		source.notifyEventToListener();
		
		//�Ƴ�������1
		source.removeListener(listener1);
		source.notifyEventToListener();
		
	}

}
