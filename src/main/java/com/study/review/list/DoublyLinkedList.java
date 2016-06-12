package com.study.review.list;

public class DoublyLinkedList<T> implements java.io.Serializable{

	private static final long serialVersionUID = 3853388774786644534L;
	
	//first和last默认值为null
	Node<T> first; //头节点
	Node<T> last;//尾节点
	
	int size;//节点数
	
	public DoublyLinkedList() {}

	public void addLast(T t) {
		final Node<T> l = last;
		final Node<T> newNode = new Node<T>(t, l, null); 
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}
	
	public void addFirst(T t) {
		final Node<T> f = first;
		final Node<T> newNode = new Node<T>(t, null, f);
		if (f == null) {
			first = newNode;
		} else {
			f.prev = newNode;
		}
		size++;
	}

	public int getSize() {
		return size;
	}
	
//	public Node<T> getFirst() {
//		return first;
//	}
//	
//	public T getItem(Node<T> node) {
//		return node.item;
//	}
	
	
	
	private static class Node<T> {
		T item;
		Node<T> prev;
		Node<T> next;
		
		Node(T item, Node<T> prev, Node<T> next) {
			super();
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
}
