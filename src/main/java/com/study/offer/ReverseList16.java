package com.study.offer;


class ListNode {
	int value;
	ListNode next;
 
	ListNode(int x) {
		value = x;
		next = null;
	}
}

public class ReverseList16 {

	public static ListNode revese(ListNode head) {
		if (head == null || head.next == null)
			return head;
		
		ListNode newHead = null;
		ListNode p = head;
		while (p != null) {
			ListNode next = p.next;
			p.next = newHead;
			newHead = p;
			p = next;
		}
		return newHead;
	}
	
	public static void printList(ListNode x) {
		if(x != null){
			System.out.print(x.value + " ");
			while (x.next != null) {
				System.out.print(x.next.value + " ");
				x = x.next;
			}
		}
	}

	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(6);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		
		printList(revese(n1));
	}
}
