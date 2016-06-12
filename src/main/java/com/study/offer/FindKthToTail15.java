package com.study.offer;

import java.util.HashMap;

/*
 * 输入一个链表，输出该链表的倒数第K个结点
 */
public class FindKthToTail15 {
	
	public static ListNode findKthToTail(ListNode head, int k){
		if (head == null || head.next == null)
			return head;
		
		ListNode p1 = head;
		for (int i = 0; i < k-1; i++) {
			if (p1.next != null)
				p1 = p1.next;
			else 
				return null; //	//链表没有K个元素
		}
		
		ListNode p2 = head;
		while (p1.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
			
		return p2;
	}
	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(6);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		System.out.println(findKthToTail(n1, 5).value);
	}
	
	
}
