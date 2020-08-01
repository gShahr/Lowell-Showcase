package structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class MinQueueTest {

  MinQueue<String> queue;
  MinQueue<Integer> queue2;
	
  @Before
  public void setup() {
    queue = new MinQueue<String>();
    queue2 = new MinQueue<Integer>();
  }

  @Test (timeout = 100)
  public void testQueue() {
    queue.enqueue(100, "Low priority");
    queue.enqueue(50, "Medium priority");
    queue.enqueue(25, "High priority");
    queue.enqueue(0, "Highest priority");
    assertEquals("Highest priority", queue.dequeue());
    assertEquals("High priority", queue.dequeue());
    assertEquals("Medium priority", queue.dequeue());
    assertEquals("Low priority", queue.dequeue());
  }
  
  @Test (timeout = 100)
  public void testIntegerQueue() {
    queue2.enqueue(100, 4);
    queue2.enqueue(50, 3);
    queue2.enqueue(25, 2);
    queue2.enqueue(0, 1);
    assertEquals(new Integer(1), queue2.dequeue());
    assertEquals(new Integer(2), queue2.dequeue());
    assertEquals(new Integer(3), queue2.dequeue());
    assertEquals(new Integer(4), queue2.dequeue());
  }
  
}
