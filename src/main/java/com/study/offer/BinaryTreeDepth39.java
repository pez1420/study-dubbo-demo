package com.study.offer;


class TreeNode {
	int value;
	TreeNode leftNode;
	TreeNode rightNode;
	TreeNode(int x) { 
		value = x; 
	}
}

public class BinaryTreeDepth39 {

	public static int deepth(TreeNode root) {
		if (root == null)
			return 0;
		int left = deepth(root.leftNode);
		int right = deepth(root.leftNode);
		//既有左子树又有右子树, 那么树的深度就是当前较大值加1
		return left > right ? left + 1 : right + 1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
