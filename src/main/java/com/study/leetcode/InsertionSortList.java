package com.study.leetcode;


/**
 * Sort a linked list using insertion sort.
 */
class ListNode {
	int value;
	ListNode next;
 
	ListNode(int x) {
		value = x;
		next = null;
	}
}
 
public class InsertionSortList {
	public static ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
 
		ListNode newHead = new ListNode(head.value);
		ListNode pointer = head.next;
		// loop through each element in the list
		while (pointer != null) {
			// insert this element to the new list
			ListNode innerPointer = newHead;
			ListNode next = pointer.next;
			if (pointer.value <= newHead.value) {
				ListNode oldHead = newHead;
				newHead = pointer;
				newHead.next = oldHead;
			} else {
				while (innerPointer.next != null) {
					if (pointer.value > innerPointer.value && pointer.value <= innerPointer.next.value) {
						ListNode oldNext = innerPointer.next;
						innerPointer.next = pointer;
						pointer.next = oldNext;
					}
					innerPointer = innerPointer.next;
				}
				if (innerPointer.next == null && pointer.value > innerPointer.value) {
					innerPointer.next = pointer;
					pointer.next = null;
				}
			}
			// finally
			pointer = next;
		}
 
		return newHead;
	}
 
	public static void main(String[] args) {
		ListNode n1 = new ListNode(2);
		ListNode n2 = new ListNode(3);
		ListNode n3 = new ListNode(4);
 
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(4);
		ListNode n6 = new ListNode(5);
 
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
 
		n1 = insertionSortList(n1);
 
		printList(n1);
 
	}
 
	public static void printList(ListNode x) {
		if(x != null){
			System.out.print(x.value + " ");
			while (x.next != null) {
				System.out.print(x.next.value + " ");
				x = x.next;
			}
			System.out.println();
		}
 
	}
}