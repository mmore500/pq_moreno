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

		// ensure that max heap property satisfied
		this.percolateUpLeaf();
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
		// check precondition
		if(this.size() == 0) {
			new AssertionError("Attempted to get the top priority in an empty queue.");
			return -1;
		}
		// if precondition is satisfied, the top priority element is the root
		return this.heap.get(0).priority;
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
		// check precondition
		if(this.size() == 0) {
			new AssertionError("Attempted to get the top element in an empty queue.");
			return -1;
		}
		// if precondition is satisfied, the top priority element is the root
		return this.heap.get(0).element;
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
	 *  <li> The assigned priority is non-negative</li>
	 *	</ul>
	 */
	public void changePriority(int element, int newpriority) {
		Integer index = this.location.get(element);

		// check precondition of non-negativity
		if(newpriority < 0) {
			new AssertionError("Attempted to set a negative priority");
		}

		// check precondition of existance
		if(index == null) {
			new AssertionError("Attempted to set the priority of element not in queue.");
		}

		this.heap.get(index).priority = newpriority;

		// adjust position of element in queue to match new priority
		// if percolating up doesn't do anything, then we might need
		// to push down
		if (this.percolateUp(index) == index) {
			this.pushDown(index);
		}
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
		Integer index = this.location.get(element);

		// check precondition
		if(index == null) {
			new AssertionError("Attempted to get the priority of element not in queue.");
			return -1;
		}

		return this.heap.get(index).priority;
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
		// if we are at the top of the tree, we can't percolate up any further
		if (this.isLeaf(start_index)) return start_index;

		int leftPri = this.getLeft(start_index).priority;
		int rightPri = this.getRight(start_index).priority;
		int highestPriChild = (PriorityQueue.compare(leftPri, rightPri) ? this.left(start_index) : this.right(start_index));

		// if we need to swap cur node with greatest child,
		// handle this recursively
		if(PriorityQueue.compare(this.heap.get(highestPriChild).priority, this.heap.get(start_index).priority)) {
			this.swap(start_index, highestPriChild);
			return this.pushDown(highestPriChild);
		}
		// otherwise, we are done sinking
		 else {
			return start_index;
		}
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
		if(PriorityQueue.compare(this.heap.get(start_index).priority, this.heap.get(parent).priority)) {
			this.swap(start_index, parent);
			return this.percolateUp(parent);
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

	protected static int parent(int child) {
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
		return this.pushDown(0);
	}

	/**
	 * Percolate up the last leaf in the heap, i.e. the most recently
	 * added element which is stored in the last slot in the list
	 *
	 * @return the index in the list where the element is finally stored
	 */
	private int percolateUpLeaf(){
		return this.percolateUp(this.size() - 1);
	}

	/**
	 * Returns true if element is a leaf in the heap
	 * @param i index of element in heap
	 * @return true if element is a leaf
	 */
	protected boolean isLeaf(int i){
		return this.left(i) >= this.size();
	}

	/**
	 * Returns true if element has two children in the heap
	 * @param i index of element in the heap
	 * @return true if element in heap has two children
	 */
	protected boolean hasTwoChildren(int i) {
		return this.right(i) < this.size();
	}

	/**
	 * Determine if the left child for an index is in the tree
	 */
	protected boolean hasLeft(int parent) {
		return !this.isLeaf(parent);
	}

	/**
	 * Determine if the right child for an index is in the tree
	 */
	protected boolean hasRight(int parent) {
		return this.hasTwoChildren(parent);
	}

	/**
	 * Return the left child Pair of an index in the tree
	 */
	private Pair<Integer,Integer> getLeft(int parent) {
		return this.heap.get(this.left(parent));
	}

	/**
	 * Return the right child Pair of an index in the tree
	 */
	private Pair<Integer,Integer> getRight(int parent) {
		return this.heap.get(this.right(parent));
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
	protected void printHeap() {
		System.out.print(this.toString());
	}

	/**
	 * Print the heap as a tree
	 */
	protected void printHeapTree() {
		System.out.print(this.treeString());
	}

	/**
	 * Generate a pretty-printed tree diagram
	 * Adapted from http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 */
	private String treeString() {
	    return this.treeString(0, new StringBuilder(), true, new StringBuilder()).toString();
	}

	/**
	 * Recursive backend for pretty-printed tree diagram
	 * Adapted from http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 */
	private StringBuilder treeString(int curNode, StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    if(this.hasRight(curNode)) {
	        this.treeString(this.right(curNode), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(this.heap.get(curNode)).append("\n");
	    if(this.hasLeft(curNode)) {
	        this.treeString(this.left(curNode), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
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
