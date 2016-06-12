package com.study.dubbo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OOMTest {
	static Vector vec = null;
	
    
    static class OOM{
    
    }
    
	public static void test_1() {
        List<OOM> list = new ArrayList<OOM>();
        int i = 0;
        while(true){
            list.add(new OOM());
            if (i++ == 5000)
				try {
					Thread.sleep(2000);
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
        }
	}
	
	public static void main(String[] args) {
		vec = new Vector(10); 
		for (int i = 1; i<100; i++) 
		{ 
			Object o = new Object(); 
			vec.add(o); 
			o = null; 
		}
		
		test_1();
		synchronized(OOMTest.class) {
			try {
				OOMTest.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

}

