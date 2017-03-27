package jianzhiOffer;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import commonAlgorithms.BinaryTreeNode;
import commonAlgorithms.GraphAlgorithms;

public class ConstructBinaryTree {
	static int preSeqCursor = 0;
	
	@Test
	public void test1(){
		int[] preSeq = { 1,2,4,7,3,5,6,8 }; 
		int[] midSeq = { 4,7,2,1,5,3,8,6 }; 
		
		BinaryTreeNode root = construct( preSeq,  midSeq ,0, midSeq.length );
		List<Integer> result = new ArrayList<Integer>();
		GraphAlgorithms.bfs( root, 
				(node, res)->{ 
						res.add(node.getValue()); 
						System.out.println(node.getValue()+" visited");
				}, result );
		
		Integer[] bfsExp = {1,2,3,4,5,6,7,8};
		Assert.assertArrayEquals( bfsExp, result.toArray() );
		
	}
	
	//preSeq is the sequence of preorder , midSeq is the mid order
	public static BinaryTreeNode construct( int[] preSeq, int [] midSeq, int mBegin, int mEnd){
 		BinaryTreeNode root = search(midSeq, preSeq[preSeqCursor]);
 		root.setValue( preSeq[preSeqCursor] );
 		System.out.println(root.getValue() + " has been visited.");
 		preSeqCursor++;
		if( root.getMidSeqIndex() > mBegin ){
			//has left child
			BinaryTreeNode left = construct(preSeq, midSeq, mBegin, root.getMidSeqIndex());
 			root.setLeftChild(left);
		}
		if( root.getMidSeqIndex() < mEnd - 1 ){
			//has right child 
			BinaryTreeNode right = construct(preSeq, midSeq, root.getMidSeqIndex()+1, mEnd) ;
			root.setRightChild( right );
		}
		return root;
	}

	private static BinaryTreeNode search(int[] m, int t) {
		BinaryTreeNode node = new BinaryTreeNode();
		for( int i = 0; i < m.length ; i++){
			if( m[i] == t){
				node.setMidSeqIndex(i);
				break;
			}
		}
		return node;
	}
}

