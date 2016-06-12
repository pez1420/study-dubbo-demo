package com.study.leetcode;

import java.util.HashMap;
/**
 * ����һ�������������Ŀ��Ҫ����һ���������������ÿ��������һ�����ָ�룬ָ��ĳһ����㡣
 * �����Ƚ���һ�ֱȽ�ֱ�ӵ��㷨��˼·���Ȱ��ո���һ����������ķ�ʽ���ƣ����Ƶ�ʱ��Ѹ��ƵĽ����һ��HashMap��
 * �Ծɽ��Ϊkey���½ڵ�Ϊvalue����ô����Ŀ����Ϊ�˵ڶ���ɨ���ʱ�����ǰ��������ϣ��ѽ������ָ����ϡ�
 * ����㷨�ǱȽ������뵽�ģ��ܹ�Ҫ��������ɨ�裬����ʱ�临�Ӷ���O(2*n)=O(n)��
 * �ռ�����Ҫһ����ϣ����������ӳ�䣬���Կռ临�Ӷ�Ҳ��O(n)
 * @author Administrator
 *
 */
class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
};

//http://blog.csdn.net/linhuanmars/article/details/22463599
public class CopyListWithRandomPointer {
	public  RandomListNode copy(RandomListNode head) {
		if (head == null)
			return head;
		//�ɽڵ㣬�½ڵ�
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		
		RandomListNode newHead = new RandomListNode(head.label);
		map.put(head, newHead);
		
		RandomListNode p = newHead;
		RandomListNode q = head.next;
		//��һ��ɨ��
		while (q != null) {
			RandomListNode newNode = new RandomListNode(q.label);
			map.put(q, newNode);
			p.next = newNode;
			p = newNode;
			q = q.next;
		}
		
		p = newHead;
		q = head;
		while (q != null) {
			RandomListNode random = map.get(q.random);
			p.random = random;
			p = p.next;
			q = q.next;
		}
		
		return newHead;
	}
	
	/**
	 * ��ô��û�а취���Բ��ö���ռ��������������أ������еģ�
	 * ǰ��������Ҫһ����ϣ���ԭ���ǵ����Ƿ���һ�����ʱ�����������ָ��ָ��Ľ�㻹û�з��ʹ���
	 * ��㻹û�д���������������Ҫ���ԵĶ���ռ䡣
	 * �����ʹ�ö���ռ䣬����ֻ��ͨ����������ԭ�������ݽṹ���洢��㡣
	 * ����˼·�������ģ��������������ɨ�裬��һ��ɨ���ÿ�������и��ƣ�
	 * Ȼ��Ѹ��Ƴ������½ڵ����ԭ����next��Ҳ������������һ���ظ�����
	 * �����¾ɸ��棻�ڶ���ɨ�������ǰѾɽ������ָ�븳���½ڵ�����ָ�룬
	 * ��Ϊ�½�㶼���ھɽ�����һ�������Ը�ֵ�Ƚϼ򵥣�����node.next.random = node.random.next��
	 * ����node.next�����½�㣬��Ϊ��һ��ɨ�����Ǿ��ǰ��½����ھɽ����档�������ǰѽ������ָ�붼�Ӻ��ˣ�
	 * ���һ��ɨ�����ǰ���������������һ����ԭԭ�������ڶ�����������Ҫ��ĸ�������
	 * ��Ϊ���������Ǿ��¸��棬ֻҪ��ÿ���������ֱ���������������зָ�ɡ�
	 * ��������ܹ�������������ɨ�裬����ʱ�临�Ӷ���O(n)�������ﲢ����Ҫ����ռ䣬���Կռ临�Ӷ���O(1)��
	 * ��������ķ������������һ������ɨ�裬���ǲ���Ҫ����ռ䣬���ǱȽ�ֵ�ġ�
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
	    if(head == null)
	        return head;
	    RandomListNode node = head;
	    while(node!=null)
	    {
	        RandomListNode newNode = new RandomListNode(node.label);
	        newNode.next = node.next;
	        node.next = newNode;
	        node = newNode.next;
	    }
	    node = head;
	    while(node!=null)
	    {
	        if(node.random != null)
	            node.next.random = node.random.next;
	        node = node.next.next;
	    }
	    RandomListNode newHead = head.next;
	    node = head;
	    while(node != null)
	    {
	        RandomListNode newNode = node.next;
	        node.next = newNode.next;
	        if(newNode.next!=null)
	            newNode.next = newNode.next.next;
	        node = node.next;
	    }
	    return newHead;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
