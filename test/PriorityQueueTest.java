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

}
