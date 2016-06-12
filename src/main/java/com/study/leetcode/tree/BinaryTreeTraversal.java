package com.study.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//�ο�����: https://segmentfault.com/a/1190000003532763

class TreeNode {
	int value;
	TreeNode leftNode;
	TreeNode rightNode;
	TreeNode(int x) { 
		value = x; 
	}
}

public class BinaryTreeTraversal {
	//�����汾1
	public static void preorder(TreeNode root, List<Integer> res) {
		if (root != null) {
			res.add(root.value);
			preorder(root.leftNode, res);
			preorder(root.rightNode, res);
		}
	}
	
	//�����汾2
	public static List<Integer> preorder(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root == null) 
			return result;
		result.add(root.value);
		result.addAll(preorder(root.leftNode));
		result.addAll(preorder(root.rightNode));
		return result;
	}
	//�ǵ�����
	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (root != null)
			stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode tmp = stack.pop(); 
			res.add(tmp.value);
			if(tmp.rightNode != null) {
				stack.push(tmp.rightNode);
			}
			if (tmp.leftNode != null) {
				stack.push(tmp.leftNode);
			}
		}
		
		return res;
	}
	
	/*
	 * ��ջ�������û�����������ôֱ�ۣ���Ϊ���ǲ�������pop����ǰԪ�أ�
	 * ��Ҫ�Ȱ����������������������pop���Լ���
	 * ���������Ƚ�������ߵ����нڵ㶼push��ջ��Ȼ��������pop����¼ֵ��
	 * ÿpopһ��Ԫ�غ��ٿ�����û����������
	 * ����еĻ��������ٽ������ҽڵ��������������ߵĽڵ㶼push��ջ��������pop��
	 */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        //�Ƚ�����ߵĽڵ㶼push��ջ
        if(root != null){
            pushAllTheLeft(s, root);
        }
        
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            res.add(curr.value);
            //����������������ҽڵ��������������ߵĽڵ㶼push��ջ
            if(curr.rightNode != null){
                pushAllTheLeft(s, curr.rightNode);
            }
        }
        return res;
    }
    
    private static void pushAllTheLeft(Stack<TreeNode> s, TreeNode root){
        s.push(root);
        while(root.leftNode != null){
            root = root.leftNode;
            s.push(root);
        }
    }
    
    
    
    /**
     * ����һ�ָ�����ķ�������Ϊ���������˳����left - right - root��
     * ��Ȼ���ǲ�����ֱ�ӵõ����˳��
     * �������������Ǻܺõõ��ģ����ǿ�����root - right - left��˳���������
     * Ȼ������ӽ�������ˡ�
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        if(root != null) 
        	stk.push(root);
        LinkedList<Integer> res = new LinkedList<Integer>();
        while(!stk.isEmpty()) {
            TreeNode curr = stk.pop();
            // ������������ң������ȷ����Һ������
            if (curr.leftNode != null) 
            	stk.push(curr.leftNode);
            if (curr.rightNode != null) 
            	stk.push(curr.rightNode);
            // ������ӽ����ÿ�μӵ���ǰ��
            res.offerFirst(curr.value);
        }
        return res;
    }
    
    
    public List<Integer> postorderTraversalV1(TreeNode root) {
        Stack<PowerNode> s = new Stack<PowerNode>();
        List<Integer> res = new LinkedList<Integer>();
        if(root!=null) s.push(new PowerNode(root, false));
        while(!s.isEmpty()){
            PowerNode curr = s.peek();
            //����ǵڶ��η��ʣ��ͼ��㲢pop�ýڵ�
            if(curr.visited){
                res.add(curr.node.value);
                s.pop();
            } else {
            //����ǵ�һ�η��ʣ��ͽ��������ҽڵ����stack�����������Ѿ�������һ��
                if(curr.node.rightNode!=null) s.push(new PowerNode(curr.node.rightNode, false));
                if(curr.node.leftNode!=null) s.push(new PowerNode(curr.node.leftNode, false));
                curr.visited = true;
            }
        }
        return res;
    }
    
    private class PowerNode {
        TreeNode node;
        boolean visited;
        public PowerNode(TreeNode n, boolean v){
            this.node = n;
            this.visited = v;
        }
    }
    
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 =  new TreeNode(4);
		TreeNode n3 =  new TreeNode(2);
		TreeNode n4 =  new TreeNode(5);
		TreeNode n5 =  new TreeNode(6);
		
		root.leftNode = n2;
		root.rightNode = n3;
		n2.leftNode = n4;
		n2.rightNode = n5;
		
		List<Integer> res = new ArrayList<Integer>();
		res = preorder(root);
		System.out.println(res.toString());
		
	}

}
