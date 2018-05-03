package dp.leetcode.bfs.Zigzag103;

import dp.leetcode.BinaryTreePostorderTraversal.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zpy on 2018/5/1.
 */
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    MyStack<TreeNode> stack = new MyStack<TreeNode>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        stack.push(root);
        zigZag1Line(true);
        return res;
    }
    private void zigZag1Line(boolean ifZig){
        MyStack<TreeNode> nextStack = new MyStack<TreeNode>();
        List<Integer> lineVisit = new LinkedList<>();
        if( stack.isEmpty()){
            return ;
        }
        while(!stack.isEmpty()){
            TreeNode n = (TreeNode)stack.pop();
            lineVisit.add(n.val);
            if( ifZig) {
                nextStack.push(n.left);
                nextStack.push(n.right);
            }else {
                nextStack.push(n.right);
                nextStack.push(n.left);
            }
        }
        stack = nextStack;
        res.add(lineVisit);
        zigZag1Line(!ifZig);
    }

}
class MyStack<E> extends Stack{
    @Override
    public E push(Object item) {
        if( item == null ){
            return null;
        }
        return (E)super.push(item);
    }
}