import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A priority queue class implemented using a max heap.
 * Priorities cannot be negative.
 *
 * @author Matthew Moreno
 * @version September 16th, 2016
 *
 */
public class PriorityQueue {

	private Map<Integer, Integer> location;
	private List<Pair<Integer, Integer>> heap;

	/**
	 *  Constructs an empty priority queue
	 */
	public PriorityQueue() {
		this.location = new HashMap<Integer,Integer>();
		this.heap = new ArrayList<Pair<Integer,Integer>>();
	}

	/**
	 *  Insert a new element into the queue with the
	 *  given priority.
	 *
	 *	@param priority priority of element to be inserted
	 *	@param element element to be inserted
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The element does not already appear in the priority queue.</li>
	 *	<li> The priority is non-negative.</li>
	 *	</ul>
	 *
	 */
	public void push(int priority, int element) {
		// check that element does not already appear in priority queue
		if (this.location.containsKey(element)) {
			new AssertionError("Attempted to add an element that already appears in the priority queue.");
		}
		// check that prioririty is non-negtive
		if (priority < 0) {
			new AssertionError("Attempted to add an element with negative priority.");
		}

		// add new element to the end of the array
		this.location.put(element, this.heap.size());
		this.heap.add(new Pair<Integer, Integer>(priority, element));

		// TODO add percolate up
		new UnsupportedOperationException("push not yet implemented.");
	}

	/**
	 *  Remove the highest priority element
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 *
	 */
	public void pop(){
		// TODO: Fill in
	}


	/**
	 *  Returns the highest priority in the queue
	 *  @return highest priority value
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 */
	public int topPriority() {
		// TODO: Fill in
		new UnsupportedOperationException("topPriority not yet implemented.");
		return -1;
	}


	/**
	 *  Returns the element with the highest priority
	 *  @return element with highest priority
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The priority queue is non-empty.</li>
	 *	</ul>
	 */
	public int topElement() {
		// TODO: Fill in
		new UnsupportedOperationException("topElement not yet implemented.");
		return -1;
	}


	/**
	 *  Change the priority of an element already in the
	 *  priority queue.
	 *
	 *  @param element element whose priority is to be changed
	 *  @param newpriority the new priority
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The element exists in the priority queue</li>
	 *	</ul>
	 */
	public void changePriority(int element, int newpriority) {
		// TODO: Fill in
	}


	/**
	 *  Gets the priority of the element
	 *
	 *  @param element the element whose priority is returned
	 *  @return the priority value
	 *
	 *	<dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The element exists in the priority queue</li>
	 *	</ul>
	 */
	public int getPriority(int element) {
		// TODO: Fill in
		new UnsupportedOperationException("getPriority not yet implemented.");
		return -1;
	}

	/**
	 *  Returns true if the priority queue contains no elements
	 *  @return true if the queue contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 *  Returns true if the element exists in the priority queue.
	 *  @return true if the element exists, false otherwise
	 */
	public boolean isPresent(int element) {
		return this.location.containsKey(element);
	}

	/**
	 *  Removes all elements from the priority queue
	 */
	public void clear() {
		// TODO: Fill in
	}

	/**
	 *  Returns the number of elements in the priority queue
	 *  @return number of elements in the priority queue
	 */
	public int size() {
		return this.heap.size();
	}



	/*********************************************************
	 * 				Private helper methods
	 *********************************************************/


	/**
	 * Push down a given element
	 * @param start_index the index of the element to be pushed down
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDown(int start_index) {
		// TODO: Fill in
		new UnsupportedOperationException("pushDown not yet implemented.");
		return -1;
	}

	/**
	 * Percolate up a given element
	 * @param start_index the element to be percolated up
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUp(int start_index) {
		// if we are at the top of the tree, we can't percolate up any further
		if (start_index == 0) return 0;

		int parent = PriorityQueue.parent(start_index);

		// if we need to swap parent with cur node,
		// handle this recursively
		if(PriorityQueue.compare(this.heap.get(start_index).element, this.heap.get(parent).element)) {
			this.swap(start_index, parent);
			return this.percolateUp(start_index);
		}
		// otherwise, we are done percolating
		 else {
			return start_index;
		}
	}


	/**
	 * Swaps two elements in the priority queue by updating BOTH
	 * the list representing the heap AND the map
	 * @param i element to be swapped
	 * @param j element to be swapped
	 */
	private void swap(int i, int j) {
		// perform the swap in the map
		int old = this.location.replace(this.heap.get(i).element, j);
		assert old == i;

		old = this.location.replace(this.heap.get(j).element, i);
		assert old == j;

		// perform the swap in the heap
		Pair swap = this.heap.get(i);
		this.heap.set(i, this.heap.get(j));
		this.heap.set(j, swap);
	}

	/**
	 * Computes the index of the element's left child
	 * @param parent index of element in list
	 * @return index of element's left child in list
	 */
	protected static int left(int parent) {
		return 2 * parent + 1;
	}

	/**
	 * Computes the index of the element's right child
	 * @param parent index of element in list
	 * @return index of element's right child in list
	 */
	protected static int right(int parent) {
		return 2 * parent + 2;
	}

	/**
	 * Computes the index of the element's parent
	 * @param child index of element in list
	 * @return index of element's parent in list
	 */

	private static int parent(int child) {
		return (child - 1)/2;
	}


	/*********************************************************
	 * 	These are optional private methods that may be useful
	 *********************************************************/

	/**
	 * Compare two elements, implemented to generate a max heap
	 * @return true if el1 is ordered above el2
	 * <dt><b>Preconditions:</b><dd>
	 *	<ul>
	 *	<li> The elements are not equivalent</li>
	 * 	</ul>
	 */
	protected static boolean compare(int el1, int el2) {
		if (el1 == el2) {
			new AssertionError("Attempted compare two elements that are equivalent.");
		}
		return el1 > el2;
	}

	/**
	 * Push down the root element
	 * @return the index in the list where the element is finally stored
	 */
	private int pushDownRoot() {
		// TODO: A one-line function that calls pushDown()
		new UnsupportedOperationException("pushDownRoot not yet implemented.");
		return -1;
	}

	/**
	 * Percolate up the last leaf in the heap, i.e. the most recently
	 * added element which is stored in the last slot in the list
	 *
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUpLeaf(){
		// TODO: A one-line function that calls percolateUp()
		new UnsupportedOperationException("percolateUpLeaf not yet implemented.");
		return -1;
	}

	/**
	 * Returns true if element is a leaf in the heap
	 * @param i index of element in heap
	 * @return true if element is a leaf
	 */
	private boolean isLeaf(int i){
		return this.left(i) >= this.size();
	}

	/**
	 * Returns true if element has two children in the heap
	 * @param i index of element in the heap
	 * @return true if element in heap has two children
	 */
	private boolean hasTwoChildren(int i) {
		return this.right(i) < this.size();
	}

	private boolean hasLeft(int parent) {
		return !this.isLeaf(parent);
	}

	private boolean hasRight(int parent) {
		return this.hasTwoChildren(parent);
	}

	private int getLeft(int parent) {
		return this.heap.get(this.left(parent)).element;
	}

	private int getRight(int parent) {
		return this.heap.get(this.right(parent)).element;
	}

	/**
	 * Return a representation of the heap as a list
	 */
	public String toString() {
		return this.heap.toString();
	}


	/**
	 * Print the underlying list representation
	 */
	private void printHeap() {
		System.out.print(this.toString());
	}

	/**
	 * Print the heap as a tree
	 */
	private void printHeapTree() {
		System.out.print(this.treeString());
	}

	// inspired by http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	public String treeString() {
	    return this.printHeapTree(0, new StringBuilder(), true, new StringBuilder()).toString();
	}

	public StringBuilder printHeapTree(int curNode, StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    if(this.hasRight(curNode)) {
	        this.printHeapTree(this.right(curNode), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(this.heap.get(curNode)).append("\n");
	    if(this.hasLeft(curNode)) {
	        this.printHeapTree(this.left(curNode), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
	    }
	    return sb;
	}

	/**
	 * Print the entries in the location map
	 */
	private void printMap() {
		System.out.print(this.location.toString());
	}


}
