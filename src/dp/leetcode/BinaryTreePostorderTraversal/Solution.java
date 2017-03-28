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
		 * �ڵ�n����ջ
		 * һ·��
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
				n = stack.pollLast(); // ��ջһ�����ڵ�n
				if( n.left != null && 
						justNode.val == n.left.val ){
					//����ղŽڵ���n�����ӣ����������n������
					if (n.right != null) { // ���ڵ������ӣ������������
						stack.add(n);// n�����ӣ��Ż�ջ���ȱ�������
						n = n.right;
						// goto begin traverse
						beginTraverse = true;
						break;
					}else{//nû�����ӣ������n,������ջ
						res.add(n.val);// visit
						// goto begin pop
						beginTraverse = false;
					}
				}else { //����ղŽڵ���n�����ӣ������n , ������ջ
					res.add(n.val);// visit
					// goto begin pop
					beginTraverse = false;
				}
			}
			if( !beginTraverse ){
				break;//ջ���ˣ�ȫ���������
			}
		}
		
		return res;
	}
	
	
}
