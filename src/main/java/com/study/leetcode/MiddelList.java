package com.study.leetcode;


/**
 * 如果要寻找一个单链表的中间节点，普通的方法就是先遍历得到链表的长度，
 * 然后再通过长度遍历得到链表的中间节点。当然有一些链表通过一个特殊的头节点记录链表的长度的情况，
 * 可能要简单一些
 * 
 * 1） 使用两个指针进行遍历，快指针每次步进2，慢指针每次步进1；
 * 2） 当快指针到达链表尾部的时候，慢指针指向的就是链表的中点。
 * 这个算法的思想和经典问题“判定链表中是否存在环”的思想是一致的，但是如果不是有启发，真的是很难想出来：）。
 *
 * 因为fast的速度是slow的两倍，所以fast走的距离是slow的两倍，有 2(a+b) = a+b+c+b，
 * 可以得到a=c（这个结论很重要！）
 */
public class MiddelList {

	/*
	 * fast与slow指针
	 */
	public static ListNode getMiddle(ListNode head) {
		ListNode p = head; //走一步
		ListNode q = head; //走两步
		while (p.next != null && q.next != null && q.next.next != null) {
			p = p.next;
			q = q.next.next;
		}
		return p;
	}
	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(8);
		ListNode n2 = new ListNode(3);
		ListNode n3 = new ListNode(4);
		ListNode n4 = new ListNode(5);
		ListNode n5 = new ListNode(20);
		ListNode n6 = new ListNode(11);
		ListNode n7 = new ListNode(9);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		ListNode res = getMiddle(n1);
		System.out.println(res.value);
	}

}
