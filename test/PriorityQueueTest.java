import org.junit.Test;
import static org.junit.Assert.*;

public class PriorityQueueTest {

  @Test
  public void constructorTest() {
    PriorityQueue q = new PriorityQueue();
  }

  @Test
  public void pushPreconditionsTest() {
    PriorityQueue q = new PriorityQueue();
    // check preconditions are checked properly
    try {
      q.push(-1, 0);
      fail("pushing an element with negative didn't raise an AssertionError" );
    } catch (AssertionError expectedException) {
    }
    try {
      q.push(0, 100);
      q.push(12, 100);
      fail("pushing an element alteady in the queue didn't raise an AssertionError" );
    } catch (AssertionError expectedException) {
    }
  }

  @Test
  public void pushTest() {
    PriorityQueue q = new PriorityQueue();
    q.push(0, 100);
    q.push(12, 100);
  }

  @Test
  public void printHeapTreeTest() {
    PriorityQueue q = new PriorityQueue();
    q.push(0, 100);
    q.push(12, 10);
    q.push(15, 1);
    q.push(8, 5);
    q.printHeapTree();
  }

  @Test
  public void leftTest() {
    assertEquals(PriorityQueue.left(0), 1);
    assertEquals(PriorityQueue.left(1), 3);
    assertEquals(PriorityQueue.left(2), 5);
    assertEquals(PriorityQueue.left(3), 7);
  }

  @Test
  public void rightTest() {
    assertEquals(PriorityQueue.right(0), 2);
    assertEquals(PriorityQueue.right(1), 4);
    assertEquals(PriorityQueue.right(2), 6);
    assertEquals(PriorityQueue.right(3), 8);
  }

  @Test
  public void parentTest() {
    assertEquals(PriorityQueue.parent(1), 0);
    assertEquals(PriorityQueue.parent(2), 0);
    assertEquals(PriorityQueue.parent(3), 1);
    assertEquals(PriorityQueue.parent(4), 1);
    assertEquals(PriorityQueue.parent(5), 2);
    assertEquals(PriorityQueue.parent(6), 2);
    assertEquals(PriorityQueue.parent(7), 3);
    assertEquals(PriorityQueue.parent(8), 3);
    assertEquals(PriorityQueue.parent(9), 4);
    assertEquals(PriorityQueue.parent(10), 4);
  }

  @Test
  public void compareTest() {
    // check our assertion against equal priority
    try {
      PriorityQueue.compare(4,4);
      fail("comparing two equivalent elements didn't raise an AssertionError" );
    } catch (AssertionError expectedException) {
    }

    // make sure that ordering is consistent
    assert(PriorityQueue.compare(12, 5) != PriorityQueue.compare(5, 12));

    // ensure that we specifically have the max heap property
    assert(PriorityQueue.compare(12, 5));
  }

  @Test
  public void topPriorityTest() {
    // check our assertion against non-empty heap
    PriorityQueue q = new PriorityQueue();
    try {
      q.topPriority();
      fail("accessing the top priority in an empty queue didn't raise an AssertionError" );
    } catch (AssertionError expectedException){
    }

    q.push(5, 12);
    assertEquals(q.topPriority(), 5);

    q.push(4, 17);
    assertEquals(q.topPriority(), 5);

    q.push(6, 11);
    assertEquals(q.topPriority(), 6);
  }

  @Test
  public void topElementTest() {
    PriorityQueue q = new PriorityQueue();

    // check our assertion against non-empty heap
    try {
      q.topElement();
      fail("accessing the top element in an empty queue didn't raise an AssertionError" );
    } catch (AssertionError expectedException){
    }

    q.push(5, 12);
    assertEquals(q.topElement(), 12);

    q.push(4, 17);
    assertEquals(q.topElement(), 12);

    q.push(6, 11);
    assertEquals(q.topElement(), 11);
  }

  @Test
  public void isLeafTest() {
    PriorityQueue q = new PriorityQueue();

    q.push(5, 12);
    assertTrue(q.isLeaf(0));

    q.push(4, 17);
    assertTrue(!q.isLeaf(0));
    assertTrue(q.isLeaf(1));

    q.push(6, 11);
    assertTrue(!q.isLeaf(0));
    assertTrue(q.isLeaf(1));
    assertTrue(q.isLeaf(2));

    q.push(12, 10);
    assertTrue(!q.isLeaf(0));
    assertTrue(!q.isLeaf(1));
    assertTrue(q.isLeaf(2));
    assertTrue(q.isLeaf(3));
  }

  @Test
  public void hasTwoChildrenTest() {
    PriorityQueue q = new PriorityQueue();

    q.push(5, 12);
    assertTrue(!q.hasTwoChildren(0));

    q.push(4, 17);
    assertTrue(!q.hasTwoChildren(0));
    assertTrue(!q.hasTwoChildren(1));

    q.push(6, 11);
    assertTrue(q.hasTwoChildren(0));
    assertTrue(!q.hasTwoChildren(1));
    assertTrue(!q.hasTwoChildren(2));

    q.push(12, 10);
    assertTrue(q.hasTwoChildren(0));
    assertTrue(!q.hasTwoChildren(1));
    assertTrue(!q.hasTwoChildren(2));
    assertTrue(!q.hasTwoChildren(3));

    q.push(12, 13);
    assertTrue(q.hasTwoChildren(0));
    assertTrue(q.hasTwoChildren(1));
    assertTrue(!q.hasTwoChildren(2));
    assertTrue(!q.hasTwoChildren(3));
    assertTrue(!q.hasTwoChildren(4));
  }

  @Test
  public void hasLeftTest() {
    PriorityQueue q = new PriorityQueue();

    q.push(5, 12);
    assertTrue(!q.hasLeft(0));

    q.push(4, 17);
    assertTrue(q.hasLeft(0));
    assertTrue(!q.hasLeft(1));

    q.push(6, 11);
    assertTrue(q.hasLeft(0));
    assertTrue(!q.hasLeft(1));
    assertTrue(!q.hasLeft(2));

    q.push(12, 10);
    assertTrue(q.hasLeft(0));
    assertTrue(q.hasLeft(1));
    assertTrue(!q.hasLeft(2));
    assertTrue(!q.hasLeft(3));

    q.push(12, 13);
    assertTrue(q.hasLeft(0));
    assertTrue(q.hasLeft(1));
    assertTrue(!q.hasLeft(2));
    assertTrue(!q.hasLeft(3));
    assertTrue(!q.hasLeft(4));
  }

  @Test
  public void hasRightTest() {
    PriorityQueue q = new PriorityQueue();

    q.push(5, 12);
    assertTrue(!q.hasRight(0));

    q.push(4, 17);
    assertTrue(!q.hasRight(0));
    assertTrue(!q.hasRight(1));

    q.push(6, 11);
    assertTrue(q.hasRight(0));
    assertTrue(!q.hasRight(1));
    assertTrue(!q.hasRight(2));

    q.push(12, 10);
    assertTrue(q.hasRight(0));
    assertTrue(!q.hasRight(1));
    assertTrue(!q.hasRight(2));
    assertTrue(!q.hasRight(3));

    q.push(12, 13);
    assertTrue(q.hasRight(0));
    assertTrue(q.hasRight(1));
    assertTrue(!q.hasRight(2));
    assertTrue(!q.hasRight(3));
    assertTrue(!q.hasRight(4));
  }




}
