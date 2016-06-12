package com.study.leetcode;


/**
 * ���ҪѰ��һ����������м�ڵ㣬��ͨ�ķ��������ȱ����õ�����ĳ��ȣ�
 * Ȼ����ͨ�����ȱ����õ�������м�ڵ㡣��Ȼ��һЩ����ͨ��һ�������ͷ�ڵ��¼����ĳ��ȵ������
 * ����Ҫ��һЩ
 * 
 * 1�� ʹ������ָ����б�������ָ��ÿ�β���2����ָ��ÿ�β���1��
 * 2�� ����ָ�뵽������β����ʱ����ָ��ָ��ľ���������е㡣
 * ����㷨��˼��;������⡰�ж��������Ƿ���ڻ�����˼����һ�µģ������������������������Ǻ��������������
 *
 * ��Ϊfast���ٶ���slow������������fast�ߵľ�����slow���������� 2(a+b) = a+b+c+b��
 * ���Եõ�a=c��������ۺ���Ҫ����
 */
public class MiddelList {

	/*
	 * fast��slowָ��
	 */
	public static ListNode getMiddle(ListNode head) {
		ListNode p = head; //��һ��
		ListNode q = head; //������
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
