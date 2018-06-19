package dfs.leetcode.RecoverBinarySearchTree99;

import dp.leetcode.BinaryTreePostorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Morris Traversal to achieve constant space cost
 * Created by zhaopengyang on 2018/5/7.
 */
public class Solution {
    int firstToSwap ;
    int secondToSwap;

    List<Integer> toSwap = new ArrayList<Integer>();

    public void recoverTree(TreeNode root) {

        TreeNode node = root, parent = null;
        //go to left most
        while( root.left != null ) {
            //thread here?
            parent = node;
            node = node.left;
        }
        //node is leftChild
        forLeftChild(node, parent);
        visit(node);
        visit(parent);
        //new iterate
        node = parent.right;


        //visit left

        //visit node
    }
    void forLeftChild(TreeNode leftChild, TreeNode parent){
        //if child is leaf, left child point to its parent, right child points to parent
        if( leftChild != null && leftChild.right == null ) {
            leftChild.right = parent;
        }

    }
    void forRightChild(TreeNode rightChild, TreeNode grandParent){
        if( rightChild != null && rightChild.right == null ){
            rightChild.right = grandParent;
        }
        visit(rightChild);
    }

    private void traverse(TreeNode node, TreeNode parent) {
        //TODO node parent not null


    }

    private void visit(TreeNode node) {

    }


}
