package dp.leetcode.BinaryTreePostorderTraversal;

import java.util.LinkedList;
import java.util.List;

public class Solution {

	public List<Integer> postorderTraversal(TreeNode root){
		boolean isLeaf = false;
		LinkedList<Integer> res = new LinkedList<Integer>();
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		
		if( root == null)
			return res; 
		
		TreeNode n = root;
		boolean beginTraverse = false;
		/*
		 * 节点n，入栈
		 * 一路左
		 * 
		 */
		while(true){
			stack.add( n );
			
			while( !isLeaf ){//begin traverse
				//walk all left
				while( n.left != null ){
					stack.add( n.left );
					n = n.left;
				}
				//no left now, turn to right if any
				if( n.right != null ){
					stack.add( n.right );
					n = n.right;
				}else{//no left, no right, so leaf
					isLeaf = true;
				}
			}
			//now it's leaf, backstracing 
			while ( !stack.isEmpty() ) {// begin pop
				isLeaf = false;
				TreeNode justNode = n;
				n = stack.pollLast(); // 出栈一个父节点n
				if( n.left != null && 
						justNode.val == n.left.val ){
					//如果刚才节点是n的左子，则继续遍历n的右子
					if (n.right != null) { // 父节点有右子，则遍历右子树
						stack.add(n);// n有右子，放回栈，先遍历右子
						n = n.right;
						// goto begin traverse
						beginTraverse = true;
						break;
					}else{//n没有右子，则访问n,继续出栈
						res.add(n.val);// visit
						// goto begin pop
						beginTraverse = false;
					}
				}else { //如果刚才节点是n的右子，则访问n , 继续出栈
					res.add(n.val);// visit
					// goto begin pop
					beginTraverse = false;
				}
			}
			if( !beginTraverse ){
				break;//栈空了，全部遍历完毕
			}
		}
		
		return res;
	}
	
	
}
