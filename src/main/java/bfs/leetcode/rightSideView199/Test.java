package bfs.leetcode.rightSideView199;

import dp.leetcode.BinaryTreePostorderTraversal.TreeNode;

/**
 * Created by zpy on 2018/5/1.
 */
public class Test {
    public  static  void main(String[] args){
        TreeNode root  = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(3);
        root.left = l1;
        root.right = r1;

        Solution solution = new Solution();
        solution.rightSideView(root);
    }
}
