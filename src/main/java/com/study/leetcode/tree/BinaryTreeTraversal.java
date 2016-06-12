package com.study.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//参考资料: https://segmentfault.com/a/1190000003532763

class TreeNode {
	int value;
	TreeNode leftNode;
	TreeNode rightNode;
	TreeNode(int x) { 
		value = x; 
	}
}

public class BinaryTreeTraversal {
	//迭代版本1
	public static void preorder(TreeNode root, List<Integer> res) {
		if (root != null) {
			res.add(root.value);
			preorder(root.leftNode, res);
			preorder(root.rightNode, res);
		}
	}
	
	//迭代版本2
	public static List<Integer> preorder(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root == null) 
			return result;
		result.add(root.value);
		result.addAll(preorder(root.leftNode));
		result.addAll(preorder(root.rightNode));
		return result;
	}
	//非迭代版
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
	 * 用栈中序遍历没有先序遍历那么直观，因为我们不能马上pop出当前元素，
	 * 而要先把它的左子树都遍历完才能pop它自己。
	 * 所有我们先将将最左边的所有节点都push进栈，然后再依次pop并记录值，
	 * 每pop一个元素后再看它有没有右子树，
	 * 如果有的话，我们再将它的右节点和右子树中最左边的节点都push进栈，再依次pop。
	 */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        //先将最左边的节点都push进栈
        if(root != null){
            pushAllTheLeft(s, root);
        }
        
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            res.add(curr.value);
            //如果有右子树，将右节点和右子树的最左边的节点都push进栈
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
     * 还有一种更巧妙的方法，因为后序遍历的顺序是left - right - root，
     * 虽然我们不方便直接得到这个顺序，
     * 但是它的逆序还是很好得到的，我们可以用root - right - left的顺序遍历树，
     * 然后反向添加结果就行了。
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        if(root != null) 
        	stk.push(root);
        LinkedList<Integer> res = new LinkedList<Integer>();
        while(!stk.isEmpty()) {
            TreeNode curr = stk.pop();
            // 先添加左后添加右，就是先访问右后访问左
            if (curr.leftNode != null) 
            	stk.push(curr.leftNode);
            if (curr.rightNode != null) 
            	stk.push(curr.rightNode);
            // 反向添加结果，每次加到最前面
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
            //如果是第二次访问，就计算并pop该节点
            if(curr.visited){
                res.add(curr.node.value);
                s.pop();
            } else {
            //如果是第一次访问，就将它的左右节点加入stack，并设置其已经访问了一次
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
