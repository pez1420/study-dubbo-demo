package com.study.offer;

public class FindCommonNode37 {

	/**
	 * ���ص�����ĳ���
	 * 
	 * @param head
	 * 		ͷָ��
	 * 
	 * @return
	 */
	public int getListNodeLength(ListNode head) {
		int len = 0;
		ListNode p = head;
		while (p != null) {
			len++;
			p = p.next;
		}
		return len;
	}
	
	/**
	 * �ҵ���һ�������ڵ�
	 * 
	 * @param h1
	 * 
	 * @param h2
	 * 
	 * @return
	 */
	public ListNode findFirstCommonNode(ListNode h1, ListNode h2) {
		int len1 = getListNodeLength(h1);
		int len2 = getListNodeLength(h2);
		int d = len1 - len2;
		
		ListNode longHead = h1;
		ListNode shortHead = h2;
		if (len1 < len2) {
			longHead = h2;
			shortHead = h1;
			d = len2 - len1;
		}
		
		for (int i = 0; i < d; i++) {
			longHead = longHead.next;
		}

		while (longHead != null && shortHead != null && longHead != shortHead) {
			longHead = longHead.next;
			shortHead = shortHead.next;
		}
		
		return longHead;
	}
}
