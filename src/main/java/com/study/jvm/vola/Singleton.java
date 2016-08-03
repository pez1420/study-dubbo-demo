package com.study.jvm.vola;

public class Singleton {
	//private volatile static Singleton instance;
	//½ûÖ¹ÖØÅÅĞò
	private  volatile static Singleton instance;
	
	private Singleton(){};
	
	
	public static Singleton getInstance_wrong() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}
	
	//DCLË«ÖØ¼ì²éËø  bug
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized(Singleton.class) {
				if (instance == null)
					instance = new Singleton();
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Singleton.getInstance();
	}
	

}
