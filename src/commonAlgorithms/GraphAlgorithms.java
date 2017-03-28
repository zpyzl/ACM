package commonAlgorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAlgorithms <T> {

	public static <T> Collection<T> bfs( Node root, CollectVisit cv, Collection<T> visitRes) {
		cv.visit(root, visitRes);
		visitChildren( root.getChildren(), cv, visitRes );
		return visitRes;
	}
	
	private static <T> void visitChildren(Queue<Node> children, CollectVisit cv, Collection<T> visitRes) {
		Queue<Node> q2 = new LinkedList<Node>();
		while( !children.isEmpty() ){
			Node n = children.poll();
			cv.visit(n, visitRes);
			q2.addAll( n.getChildren( ) );
		}
		if( !q2.isEmpty() ){
			visitChildren(q2, cv, visitRes);
		}
	}
}
