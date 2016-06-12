package com.study.leetcode;

import java.util.HashMap;
/**
 * 这是一道链表操作的题目，要求复制一个链表，不过链表的每个结点带有一个随机指针，指向某一个结点。
 * 我们先介绍一种比较直接的算法，思路是先按照复制一个正常链表的方式复制，复制的时候把复制的结点做一个HashMap，
 * 以旧结点为key，新节点为value。这么做的目的是为了第二遍扫描的时候我们按照这个哈希表把结点的随机指针接上。
 * 这个算法是比较容易想到的，总共要进行两次扫描，所以时间复杂度是O(2*n)=O(n)。
 * 空间上需要一个哈希表来做结点的映射，所以空间复杂度也是O(n)
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
		//旧节点，新节点
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		
		RandomListNode newHead = new RandomListNode(head.label);
		map.put(head, newHead);
		
		RandomListNode p = newHead;
		RandomListNode q = head.next;
		//第一次扫描
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
	 * 那么有没有办法可以不用额外空间来完成这个任务呢？还是有的，
	 * 前面我们需要一个哈希表的原因是当我们访问一个结点时可能它的随机指针指向的结点还没有访问过，
	 * 结点还没有创建，所以我们需要线性的额外空间。
	 * 想避免使用额外空间，我们只能通过利用链表原来的数据结构来存储结点。
	 * 基本思路是这样的，对链表进行三次扫描，第一次扫描对每个结点进行复制，
	 * 然后把复制出来的新节点接在原结点的next，也就是让链表变成一个重复链表，
	 * 就是新旧更替；第二次扫描中我们把旧结点的随机指针赋给新节点的随机指针，
	 * 因为新结点都跟在旧结点的下一个，所以赋值比较简单，就是node.next.random = node.random.next，
	 * 其中node.next就是新结点，因为第一次扫描我们就是把新结点接在旧结点后面。现在我们把结点的随机指针都接好了，
	 * 最后一次扫描我们把链表拆成两个，第一个还原原链表，而第二个就是我们要求的复制链表。
	 * 因为现在链表是旧新更替，只要把每隔两个结点分别相连，对链表进行分割即可。
	 * 这个方法总共进行三次线性扫描，所以时间复杂度是O(n)。而这里并不需要额外空间，所以空间复杂度是O(1)。
	 * 比起上面的方法，这里多做一次线性扫描，但是不需要额外空间，还是比较值的。
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
