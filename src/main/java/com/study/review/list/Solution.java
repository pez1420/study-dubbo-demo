package com.study.review.list;

class Node {
	int value;
	Node next;
	
	Node() {}
	
	Node(int value) {
		this.value = value;
		this.next = null;
	}
}

public class Solution {

	public Node merge(Node head1, Node head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;
		
		Node mergeHead = null;
		if (head1.value < head2.value) {
			mergeHead = head1;
		}
		
		return mergeHead;
	}
	
	public Node MergeLists(Node list1, Node list2) {
		  if (list1 == null) return list2;
		  if (list2 == null) return list1;

		  Node head;
		  if (list1.value < list2.value) {
		    head = list1;
		  } else {
		    head = list2;
		    list2 = list1;
		    list1 = head;
		  }
		  while(list1.next != null && list2 != null) {
		    if (list1.next.value > list2.value) {
		      Node tmp = list1.next;
		      list1.next = list2;
		      list2 = tmp;
		    }
		    list1 = list1.next;
		  } 
		  if (list1.next == null) list1.next = list2;
		  return head;
	}
}
