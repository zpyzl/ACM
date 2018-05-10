package dfs.leetcode.RecoverBinarySearchTree99;

import dp.leetcode.BinaryTreePostorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaopengyang on 2018/5/7.
 */
public class Solution {
    Integer first ;
    Integer second ;
    List<Integer> toSwap = new ArrayList<Integer>();

    public void recoverTree(TreeNode root) {
        traverse(root.left);


        traverse(root.right);
    }

    private void traverse(TreeNode node) {
        traverse(node.left);

        if( first == null && second == null ){
            first = node.val;
        }else if( first != null && second == null ){
            second = node.val;
        }else {
            first = second;
            second = node.val;
            if( first > second ){
                toSwap.add();
            }
        }

        traverse(node.right);
    }

    private void visit(TreeNode node) {

    }


}
