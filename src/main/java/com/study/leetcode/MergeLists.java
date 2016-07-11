package com.study.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Node {
	int value;
	Node next;

	Node() {
	}

	Node(int value) {
		this.value = value;
		this.next = null;
	}
}

public class MergeLists {
	
	//迭代版本
	public Node mergeRec(Node head1, Node head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;
		
		Node mergeHead = null;
		if (head1.value < head2.value) {
			mergeHead = head1;
			mergeHead.next = mergeRec(head1.next, head2);
		} else {
			mergeHead = head2;
			mergeHead.next = mergeRec(head1, head2.next);
		}
		return mergeHead;
	}
	
	
	
	public Node merge(Node head1, Node head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;

		// head1始终指向mergeHead链表的最后一个元素
		Node mergeHead = null;
		if (head1.value < head2.value) {
			mergeHead = head1;
		} else {
			mergeHead = head2;
			head2 = head1;
			head1 = mergeHead;
		}

		//head1指向当前值比较小的链表
		while (head1.next != null && head2 != null) {
			if (head1.next.value > head2.value) {
				Node tmp = head1.next;
				head1.next = head2;
				head2 = tmp;
			}
			head1 = head1.next; 
		}

		if (head1.next == null)
			head1.next = head2;

		return mergeHead;
	}

	//合并k个递增单向链表的解法涉及到了一些数据结构
	public ListNode mergeKLists(List<ListNode> lists) {
		if (lists.size() == 0)
			return null;

		//优先队列本质上就是一个最小堆
		// PriorityQueue is a sorted queue
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
			public int compare(ListNode a, ListNode b) {
				if (a.value > b.value)
					return 1;
				else if (a.value == b.value)
					return 0;
				else
					return -1;
			}
		});

		// add first node of each list to the queue
		for (ListNode list : lists) {
			if (list != null)
				q.add(list);
		}

		ListNode head = new ListNode(0);
		ListNode p = head; // serve as a pointer/cursor

		while (q.size() > 0) { 
			ListNode temp = q.poll();
			// poll() retrieves and removes the head of the queue - q.
			p.next = temp;

			// keep adding next element of each list
			if (temp.next != null)
				q.add(temp.next);

			p = p.next;
		}

		return head.next;
	}

	public static void printList(Node x) {
		if (x != null) {
			System.out.print(x.value + " ");
			while (x.next != null) {
				System.out.print(x.next.value + " ");
				x = x.next;
			}
			System.out.println();
		}
	}

	public static void printListNode(ListNode x) {
		if (x != null) {
			System.out.print(x.value + " ");
			while (x.next != null) {
				System.out.print(x.next.value + " ");
				x = x.next;
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		Node n1 = new Node(8);
//		Node n2 = new Node(11);
//		n1.next = n2;
//
//		Node n3 = new Node(5);
//		Node n4 = new Node(18);
//		n3.next = n4;
//		Node head = new MergeLists().merge(n1, n3);
//		printList(head);
		
		ListNode l1 = new ListNode(2);
		ListNode l2 = new ListNode(33);
		ListNode l3 = new ListNode(5);
		ListNode l4 = new ListNode(8);
		
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		lists.add(l1);
		lists.add(l2);
		lists.add(l3);
		lists.add(l4);
		ListNode p = new MergeLists().mergeKLists(lists);
		//printListNode(p);
		test();
		
	}

	public static void test() {
		 PriorityQueue<String> pq = new PriorityQueue<String>();
	        pq.add("dog");
	        pq.add("apple");
	        pq.add("fox");
	        pq.add("easy");
	        pq.add("boy");
	        
	        while (!pq.isEmpty()) {
	            for (String s : pq) {
	                System.out.print(s + " ");
	            }
	            System.out.println();
	            System.out.println("pq.poll(): " + pq.poll());
	        }
	}
	
	public Node mergeSortedLists(Node list1, Node list2) {
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;

		Node head;
		if (list1.value < list2.value) {
			head = list1;
		} else {
			head = list2;
			list2 = list1;
			list1 = head;
		}
		while (list1.next != null && list2 != null) {
			if (list1.next.value > list2.value) {
				Node tmp = list1.next;
				list1.next = list2;
				list2 = tmp;
			}
			list1 = list1.next;
		}
		if (list1.next == null)
			list1.next = list2;
		return head;
	}
}
