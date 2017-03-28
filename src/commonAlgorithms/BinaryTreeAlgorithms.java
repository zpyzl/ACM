package commonAlgorithms;

import java.util.LinkedList;
import java.util.List;

import jianzhiOffer.ConstructBinaryTree;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeAlgorithms {

	boolean isLeaf = false;
	LinkedList<Integer> res = new LinkedList<Integer>();
	LinkedList<BinaryTreeNode> stack = new LinkedList<BinaryTreeNode>();

	public List<Integer> postorderTraversalIterative(BinaryTreeNode root){
		if( root == null)
			return res; 
		
		BinaryTreeNode n = root;
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
				while( n.getLeftChild() != null ){
					stack.add( n.getLeftChild() );
					n = n.getLeftChild();
				}
				//no left now, turn to right if any
				if( n.getRightChild() != null ){
					stack.add( n.getRightChild() );
					n = n.getRightChild();
				}else{//no left, no right, so leaf
					isLeaf = true;
				}
			}
			//now it's leaf, backstracing 
			while ( !stack.isEmpty() ) {// begin pop
				isLeaf = false;
				BinaryTreeNode justNode = n;
				n = stack.pollLast(); // ��ջһ�����ڵ�n
				if( n.getLeftChild() != null && 
						justNode.getValue() == n.getLeftChild().getValue() ){
					//����ղŽڵ���n�����ӣ����������n������
					if (n.getRightChild() != null) { // ���ڵ������ӣ������������
						stack.add(n);// n�����ӣ��Ż�ջ���ȱ�������
						n = n.getRightChild();
						// goto begin traverse
						beginTraverse = true;
						break;
					}else{//nû�����ӣ������n,������ջ
						res.add(n.getValue());// visit
						// goto begin pop
						beginTraverse = false;
					}
				}else { //����ղŽڵ���n�����ӣ������n , ������ջ
					res.add(n.getValue());// visit
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
	
	public static List<Integer> postorderTraversalRecursive(
			BinaryTreeNode root, LinkedList<Integer> res){
		if( root.getLeftChild() != null)
			postorderTraversalRecursive(root.getLeftChild(),res);
		if( root.getRightChild() != null)
			postorderTraversalRecursive(root.getRightChild(),res);
		res.add(root.getValue());
		return res;
	}
	
	@Test
	public void testGeneral(){
		LinkedList<Integer> res = new LinkedList<Integer>();
		
		int[] preSeq = { 1,2,4,7,3,5,6,8 }; 
		int[] midSeq = { 4,7,2,1,5,3,8,6 }; 
		BinaryTreeNode root = ConstructBinaryTree.construct( preSeq,  midSeq ,0, midSeq.length );
		Object[] expecteds =  postorderTraversalRecursive(root, res).toArray();
		Object[] actuals =  postorderTraversalIterative(root).toArray();
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void testFor1(){
		LinkedList<Integer> res = new LinkedList<Integer>();
		
		BinaryTreeNode root = new BinaryTreeNode();
		root.setValue(1);
		Object[] actuals =  postorderTraversalIterative(root).toArray();
		Integer[] expects = {1};
		Assert.assertArrayEquals(expects, actuals);
	}
	@Test
	public void testForNull(){
		
		Object[] expecteds = {} ;
		Object[] actuals = postorderTraversalIterative(null).toArray();
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
}
