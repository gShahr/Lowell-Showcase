package structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class MaxQueueTest {

  MaxQueue<String> queue;
  MaxQueue<Integer> queue2;
	
  @Before
  public void setup() {
    queue = new MaxQueue<String>();
    queue2 = new MaxQueue<Integer>();
  }

  @Test (timeout = 100)
  public void testQueue() {
    queue.enqueue(100, "Highest priority");
    queue.enqueue(50, "High priority");
    queue.enqueue(25, "Medium priority");
    queue.enqueue(0, "Low priority");
    assertEquals("Highest priority", queue.dequeue());
    assertEquals("High priority", queue.dequeue());
    assertEquals("Medium priority", queue.dequeue());
    assertEquals("Low priority", queue.dequeue());
  }
  
  @Test (timeout = 100)
  public void testIntegerQueue() {
    queue2.enqueue(100, 1);
    queue2.enqueue(50, 2);
    queue2.enqueue(25, 3);
    queue2.enqueue(0, 4);
    assertEquals(new Integer(1), queue2.dequeue());
    assertEquals(new Integer(2), queue2.dequeue());
    assertEquals(new Integer(3), queue2.dequeue());
    assertEquals(new Integer(4), queue2.dequeue());
  }  
  
  @Test (timeout = 100, expected = IllegalStateException.class)
  public void testIllegalState() {
	  queue.dequeue();
	  queue2.dequeue();
	  queue.peek();
	  queue2.peek();
  }
  
  @Test (timeout = 100, expected = NullPointerException.class)
  public void testNull() {
	  queue.enqueue(null, null);
	  queue2.enqueue(new Integer (2), null);
  }
  
}
