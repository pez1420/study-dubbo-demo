package com.study.leetcode;


//https://www.douban.com/note/330562764/

/**
 * 
 * Sort a linked list in O(n log n) time using constant space complexity. Merge
 * sort.
 * 
 * @author Administrator
 *
 */
public class SortedList {
	//http://www.sjsjw.com/kf_code/article/034081ABA000189.asp
	
	public Node sortList(Node head) {
		if (head == null || head.next == null)
			return head;
		Node p = getMid(head);
		Node q;
		q = p.next;
		p.next = null;
		p = head;
		p = sortList(p);
		q = sortList(q);
		return merge(p, q);
	}

	public Node getMid(Node head) {
		Node p = head;
		Node q = head;
		while (p.next != null && q.next != null && q.next.next != null) {
			p = p.next;
			q = q.next.next;
		}
		return p;
	}

	public Node merge(Node p, Node q) {
		Node r = new Node(0);
		Node res = r;
		while (p != null || q != null) {
			int a, b;
			if (p == null)
				a = Integer.MAX_VALUE;
			else
				a = p.value;

			if (q == null)
				b = Integer.MAX_VALUE;
			else
				b = q.value;

			if (a < b) {
				r.next = p;
				p = p.next;
			} else {
				r.next = q;
				q = q.next;
			}
			r = r.next;
		}
		return res.next;
	}
	
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(12);
		Node n3 = new Node(7);
		Node n4 = new Node(2);
		Node n5 = new Node(6);
		Node n6 = new Node(4);
		Node n7 = new Node(11);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		printList(n1);
		SortedList sortedList = new SortedList();
		sortedList.sortList(n1);
		printList(n1);
		
	}
	
	public static void printList(Node x) {
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
