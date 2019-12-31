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
		if (root == null) {
			return 0;
		}

		int left = deepth(root.leftNode);
		int right = deepth(root.leftNode);
		// 二叉树的根节点既有右子树又有左子树，那么可以判断，那么二叉树的深度应该是其左右子树的深度较大值加1

		return left > right ? left + 1 : right + 1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
