package com.study.review.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestTravel {

	public static void test1() {
		List<Integer> list = new ArrayList<Integer>();
		for(Integer i: list){

		}

	}
	
	public static void test2() {
		List<Integer> list = new ArrayList<Integer>();
		for(Iterator<Integer> iterator = list.iterator(); iterator.hasNext();){
		    Integer i = iterator.next();

		}
	}
	public static void test4() {
		DoublyLinkedList<Integer> la = new DoublyLinkedList<Integer>();
		la.addLast(1);
		la.addLast(3);
		la.addLast(5);
		DoublyLinkedList<Integer> lb = new DoublyLinkedList<Integer>();
		lb.addLast(2);
		lb.addLast(4);
		lb.addLast(9);
		
		
	}
	
	public static void test3() {
		//http://stackoverflow.com/questions/17745362/merging-two-sorted-linked-list-of-unequal-length
		List<Integer> la = new LinkedList<Integer>();
		la.add(1);
		la.add(2);
		la.add(3);
		
		List<Integer> lb = new LinkedList<Integer>();
		lb.add(2);
		lb.add(4);
		lb.add(6);
		
		List<Integer> lc = new LinkedList<Integer>();
		
		ListIterator<Integer> iteratora = la.listIterator();
		ListIterator<Integer> iteratorb = lb.listIterator();
		while (iteratora.hasNext()){
			Integer a = iteratora.next();
			while (iteratorb.hasNext()){
				Integer b = iteratorb.next();
				if (a.intValue() < b.intValue()) {
					lc.add(a);
					iteratora.remove();
					iteratorb = lb.listIterator();
					break;
				} else if (a.intValue() > b.intValue()){
					lc.add(b);
					iteratorb.remove();
					iteratorb = lb.listIterator();
					continue;
				} else {
					lc.add(a);
					lc.add(b);
					iteratora.remove();
					iteratorb.remove();
					iteratora = la.listIterator();
					iteratorb = lb.listIterator();
					break;
				}
			}
			iteratora = la.listIterator();
		}	
		
		while (iteratora.hasNext()){
			lc.add(iteratora.next());
		}
		
		while (iteratorb.hasNext()){
			lc.add(iteratorb.next());
		}
		
		System.out.println(lc.toString());
	}
	
	public static void main(String[] args) {
		test3();

	}

}
