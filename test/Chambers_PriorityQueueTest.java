import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Chambers_PriorityQueueTest {
	PriorityQueue pq;

	@Before
	public void setUp() {
		pq = new PriorityQueue();
	}

	// Pushing a negative priority
	@Test(expected=AssertionError.class)
	public void testPushNegative() {
		pq.push(-10,  0);
	}

	// Pushing the same element twice
	@Test(expected=AssertionError.class)
	public void testDoublePush(){
		pq.push(10, 5);
		pq.push(11, 5);
	}

	@Test
	public void testPush(){
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);
		assertEquals(3, pq.size());
		assertEquals(33, (int)pq.heap.get(0).priority);
		assertEquals(10, (int)pq.heap.get(1).priority);
		assertEquals(11, (int)pq.heap.get(2).priority);
		assertEquals(0, (int)pq.location.get(20));
		assertEquals(1, (int)pq.location.get(15));
		assertEquals(2, (int)pq.location.get(40));
	}

	// Calling pop on empty queue
	@Test(expected=AssertionError.class)
	public void testPopEmptyQueue() {
		pq.pop();
	}

	// Calling top and pop on non-empty queue
	@Test
	public void testTopAndPop(){
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);

		assertEquals(3, pq.size());
		assertEquals(33, pq.topPriority());
		assertEquals(20, pq.topElement());

		pq.pop();
		assertEquals(2, pq.size());

		assertEquals(11, pq.topPriority());
		assertEquals(40, pq.topElement());

		pq.pop();
		assertEquals(1, pq.size());

		assertEquals(10, pq.topPriority());
		assertEquals(15, pq.topElement());

		pq.pop();
		assertEquals(0, pq.size());
	}

	// Calling top priority on empty queue
	@Test(expected=AssertionError.class)
	public void testTopPriorityEmptyQueue() {
		pq.topPriority();
	}

	// Calling top element on empty queue
	@Test(expected=AssertionError.class)
	public void testTopElementEmptyQueue() {
		pq.topElement();
	}

	// Calling change priority on an empty queue
	@Test(expected=AssertionError.class)
	public void testChangePriorityElementNotPresent() {
		pq.changePriority(3, 5);
	}

	@Test
	public void testChangePriorityIncrease() {
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);

		pq.changePriority(15,  100);

		assertEquals(100, pq.topPriority());
		assertEquals(15, pq.topElement());

		pq.pop();
		assertEquals(33, pq.topPriority());
		assertEquals(20, pq.topElement());

		pq.pop();
		assertEquals(11, pq.topPriority());
		assertEquals(40, pq.topElement());
	}

	@Test
	public void testChangePriorityDecreaseRoot() {
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);

		pq.changePriority(20,  1);

		assertEquals(11, pq.topPriority());
		assertEquals(40, pq.topElement());

		pq.pop();
		assertEquals(10, pq.topPriority());
		assertEquals(15, pq.topElement());

		pq.pop();
		assertEquals(1, pq.topPriority());
		assertEquals(20, pq.topElement());
	}

	@Test
	public void testChangePriorityDecreaseNonRoot() {
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);

		pq.changePriority(15,  50);

		assertEquals(50, pq.topPriority());
		assertEquals(15, pq.topElement());

		pq.pop();
		assertEquals(33, pq.topPriority());
		assertEquals(20, pq.topElement());

		pq.pop();
		assertEquals(11, pq.topPriority());
		assertEquals(40, pq.topElement());
	}

	@Test(expected=AssertionError.class)
	public void testGetPriorityElementNotPresent() {
		pq.getPriority(100);
	}

	public void testGetPriority(){
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);

		assertEquals(10, pq.getPriority(15));
		assertEquals(11, pq.getPriority(40));
		assertEquals(33, pq.getPriority(20));
	}


	@Test
	public void testIsEmptyInitially() {
		assertTrue(pq.isEmpty());
	}

	@Test
	public void testIsEmptyPopping(){
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);
		pq.pop();
		pq.pop();
		pq.pop();
		assertTrue(pq.isEmpty());
	}

	@Test
	public void testIsEmptyClear(){
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);
		pq.clear();
		assertTrue(pq.isEmpty());
	}

	@Test
	public void testIsPresent() {
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);
		assertTrue(pq.isPresent(15));
		assertTrue(pq.isPresent(40));
		assertTrue(pq.isPresent(20));
		assertFalse(pq.isPresent(10));
		assertFalse(pq.isPresent(11));
		assertFalse(pq.isPresent(33));
	}

	@Test
	public void testClear() {
		pq.push(10, 15);
		pq.push(11, 40);
		pq.push(33, 20);
		pq.clear();
		assertEquals(0, pq.size());
		assertTrue(pq.isEmpty());
	}

}
