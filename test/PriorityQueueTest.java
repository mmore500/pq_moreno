import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

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

  @Test
  public void getPriorityTest() {
    PriorityQueue q = new PriorityQueue();
    q.push(5, 12);
    q.push(4, 17);
    q.push(6, 11);
    q.push(12, 10);
    q.push(5, 14);

    // check precondition
    try {
      q.getPriority(5);
      fail("accessing a nonexistant element in a queue didn't raise an AssertionError" );
    } catch (AssertionError expectedException){
    }
    assertEquals(q.getPriority(12), 5);
    assertEquals(q.getPriority(17), 4);
    assertEquals(q.getPriority(11), 6);
    assertEquals(q.getPriority(10), 12);
    assertEquals(q.getPriority(14), 5);
  }

  @Test
  public void isEmptyTest() {
    PriorityQueue q = new PriorityQueue();
    assertTrue(q.isEmpty());
    q.push(1,1);
    assertFalse(q.isEmpty());
  }

  @Test
  public void changePriorityTest() {
    PriorityQueue q = new PriorityQueue();
    q.push(5, 12);
    q.push(4, 17);
    q.push(6, 11);
    q.push(12, 10);
    q.push(5, 14);

    // check precondition of existence
    try {
      q.changePriority(5,100);
      fail("accessing a nonexistant element in a queue didn't raise an AssertionError" );
    } catch (AssertionError expectedException){
    }

    // check precondition of non-negativity
    try {
      q.changePriority(12,-2);
      fail("setting an element to a negative priority didn't raise an AssertionError" );
    } catch (AssertionError expectedException){
    }

    q.changePriority(12, 7);
    assertEquals(q.getPriority(12), 7);
    q.changePriority(17, 8);
    assertEquals(q.getPriority(17), 8);
    q.changePriority(11, 1);
    assertEquals(q.getPriority(11), 1);
    q.changePriority(10, 22);
    assertEquals(q.getPriority(10), 22);
    q.changePriority(14, 12);
    assertEquals(q.getPriority(14), 12);
  }

  @Test
  public void popTest() {
    PriorityQueue q = new PriorityQueue();

    // check non-empty precondition
    try {
      q.pop();
      fail("popping out of an empty list didn't raise an AssertionError" );
    } catch (AssertionError expectedException){
    }

    q.push(5, 12);
    q.push(4, 17);
    q.push(6, 11);
    q.push(12, 10);
    q.push(1, 14);

    // make sure that elements are popped out
    // lowest to highest priority
    assertEquals(q.size(), 5);
    assertEquals(q.pop(), 10);
    assertEquals(q.size(), 4);
    assertEquals(q.pop(), 11);
    assertEquals(q.size(), 3);
    assertEquals(q.pop(), 12);
    assertEquals(q.size(), 2);
    assertEquals(q.pop(), 17);
    assertEquals(q.size(), 1);
    assertEquals(q.pop(), 14);
    assertEquals(q.size(), 0);

  }

  @Test
  public void clearTest() {
    PriorityQueue q = new PriorityQueue();

    assertEquals(q.size(), 0);
    q.clear();
    assertEquals(q.size(), 0);
    q.push(5, 12);
    q.push(4, 17);
    assertEquals(q.size(), 2);
    q.clear();
    assertEquals(q.size(), 0);
    q.push(6, 11);
    q.push(12, 10);
    q.push(1, 14);
    assertEquals(q.size(), 3);
    q.clear();
    assertEquals(q.size(), 0);
  }


  @Test
  public void isProperHeapTest() {
    List<Pair<Integer, Integer>> list = new ArrayList();

    list.add(new Pair(10, 1));
    list.add(new Pair(9, 2));
    list.add(new Pair(8, 3));
    list.add(new Pair(7, 4));
    list.add(new Pair(6, 5));
    list.add(new Pair(5, 6));
    list.add(new Pair(4, 7));
    list.add(new Pair(3, 8));
    list.add(new Pair(2, 9));
    list.add(new Pair(1, 10));

    assertTrue(PriorityQueue.isProperHeap(list));

    list.clear();

    list.add(new Pair(100, 1));
    list.add(new Pair(50, 2));
    list.add(new Pair(40, 3));
    list.add(new Pair(47, 4));
    list.add(new Pair(2, 5));
    list.add(new Pair(39, 6));
    list.add(new Pair(7, 7));
    list.add(new Pair(25, 8));
    list.add(new Pair(25, 9));
    list.add(new Pair(1, 10));
    assertTrue(PriorityQueue.isProperHeap(list));

    list.clear();

    list.add(new Pair(100, 11));
    list.add(new Pair(50, 2));
    list.add(new Pair(40, 3));
    list.add(new Pair(47, 4));
    list.add(new Pair(2, 5));
    list.add(new Pair(39, 6));
    list.add(new Pair(7, 7));
    list.add(new Pair(25, 8));
    list.add(new Pair(25, 9));
    list.add(new Pair(6, 10));

    assertFalse(PriorityQueue.isProperHeap(list));

    list.clear();

    list.add(new Pair(100, 11));
    list.add(new Pair(50, 2));
    list.add(new Pair(40, 3));
    list.add(new Pair(47, 4));
    list.add(new Pair(2, 5));
    list.add(new Pair(47, 6));
    list.add(new Pair(7, 7));
    list.add(new Pair(25, 8));
    list.add(new Pair(25, 9));
    list.add(new Pair(1, 10));

    assertFalse(PriorityQueue.isProperHeap(list));
  }

}
