package dfs.leetcode.RecoverBinarySearchTree99;

import dp.leetcode.BinaryTreePostorderTraversal.TreeNode;

/**
 * Created by zhaopengyang on 2018/5/7.
 */
public class Solution {
    int leftBiggest;
    int mostPosition;

    public void recoverTree(TreeNode root) {
        //every subtree whether violate bstree

    }
    void check(TreeNode root){

        int leftBiggest = getLeftBiggest();
        if( leftBiggest > root.val){
            //swap root and left child

        }
        int rightSmallest ;

    }
}
