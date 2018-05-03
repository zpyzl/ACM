package commonAlgorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeNode extends Node {

	private BinaryTreeNode leftChild;
	private BinaryTreeNode rightChild;
	
	private int midSeqIndex;


	public BinaryTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public int getMidSeqIndex() {
		return midSeqIndex;
	}

	public void setMidSeqIndex(int midSeqIndex) {
		this.midSeqIndex = midSeqIndex;
	}

	@Override
	public Queue<Node> getChildren() {
		Queue<Node> children = new LinkedList<Node>();
		if( this.getLeftChild() != null )
			children.add(this.getLeftChild());
		if( this.getRightChild() != null )
			children.add(this.getRightChild());
		return children;
	}

}
