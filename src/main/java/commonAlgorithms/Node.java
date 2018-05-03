package commonAlgorithms;

import java.util.Queue;

public abstract class Node {

	private int value;
	
	
	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public abstract Queue<Node> getChildren();
	
}


