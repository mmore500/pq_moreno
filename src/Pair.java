/**
 * A wrapper class for priority queue elements
 *
 * @author americachambers
 *
 */
public class Pair<P, E> {
	public P priority;
	public E element;

	public Pair(P p, E e) {
		this.priority = p;
		this.element = e;
	}

	public String toString() {
		return "(priority: " + this.priority + ", element: " + this.element + ")";
	}

}