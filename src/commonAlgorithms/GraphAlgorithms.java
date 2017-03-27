package commonAlgorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAlgorithms <T> {

	public static <T> Collection<T> bfs( Node root, CollectVisit cv, Collection<T> visitRes) {
		cv.visit(root, visitRes);
		visitQueue( root.getChildren(), cv, visitRes );
		return visitRes;
	}
	
	public static <T> void visitQueue(Queue<Node> q, CollectVisit cv, Collection<T> visitRes) {
		Queue<Node> q2 = new LinkedList<Node>();
		while( !q.isEmpty() ){
			Node n = q.poll();
			cv.visit(n, visitRes);
			q2.addAll( n.getChildren( ) );
		}
		if( !q2.isEmpty() ){
			visitQueue(q2, cv, visitRes);
		}
	}
}
