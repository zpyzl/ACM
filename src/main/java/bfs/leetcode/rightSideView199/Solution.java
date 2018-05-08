package bfs.leetcode.rightSideView199;

import dp.leetcode.BinaryTreePostorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zpy on 2018/5/1.
 *
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if( root == null)
            return  res;
        travel(0, root,res);
        return res;
    }

    private void travel(int level, TreeNode node, List<Integer> res) {
        if( level <= res.size() - 1  )
            res.set(level, node.val);
        else
            res.add(node.val);
        level++;
        if(node.left != null)
            travel(level, node.left, res);
        if(node.right != null)
            travel(level, node.right, res);
    }

}
