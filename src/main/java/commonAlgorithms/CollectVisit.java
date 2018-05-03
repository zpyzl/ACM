package commonAlgorithms;

import java.util.Collection;

@FunctionalInterface
public interface CollectVisit<T> {
	void visit(Node node, Collection<T> collection);
}
