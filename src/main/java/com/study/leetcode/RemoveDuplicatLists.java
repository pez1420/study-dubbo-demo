package com.study.leetcode;

public class RemoveDuplicatLists {

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode prev = head;
		ListNode p = head.next;

		while (p != null) {
			if (p.value == prev.value) {
				prev.next = p.next;
				p = p.next;
				// no change prev
			} else {
				prev = p;
				p = p.next;
			}
		}

		return head;
	}

	public ListNode deleteDuplicatesV1(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode p = head;

		while (p != null && p.next != null) {
			if (p.value == p.next.value) {
				p.next = p.next.next;
			} else {
				p = p.next;
			}
		}

		return head;
	}

}
