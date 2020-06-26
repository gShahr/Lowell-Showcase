package structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

public class ListInterfaceTest {

	private ListInterface<String> list;
	private ListInterface<Integer> list2;
	
	@Before
	public void setup(){
          list = new RecursiveList<String>();
          list2 = new RecursiveList<Integer>();
          list2.insertFirst(new Integer(1));
          list2.insertLast(new Integer(2));
	}
	
	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
	
	@Test (timeout = 5000) 
	public void testInsertLastAndGetLast() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Insert Last should return instance of self", list, list.insertLast("video"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("Last element should .equals \"video\".", "video", list.getLast());
		list.insertLast("Game");
		assertEquals(2, list.size());
		assertEquals("Last element should .equals \"Game\".", "Game", list.getLast());
	}
	
	@Test(timeout = 5000) 
	public void testRemoveFirst() {
		assertEquals(2, list2.size());
		assertEquals(new Integer(1), list2.removeFirst());
		assertEquals(1, list2.size());
		assertEquals(new Integer(2), list2.removeFirst());
		assertEquals(0, list2.size());
	}
	
	@Test(timeout = 5000) 
	public void testRemoveLast() {
		assertEquals(2, list2.size());
		assertEquals(new Integer(2), list2.removeLast());
		assertEquals(1, list2.size());
		assertEquals(new Integer(1), list2.removeLast());
		assertEquals(0, list2.size());
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveAndGetException() throws Exception {
		list.removeLast();
		list.removeFirst();
		list.getFirst();
		list.getLast();
		list.get(-1);
	}
	
	@Test(timeout = 5000) 
	public void testInsertAtAndGet() {
		list2.insertAt(2, new Integer(3));
		assertEquals(3, list2.size());
		assertEquals(new Integer(3), list2.getLast());
		list2.insertAt(0, new Integer(500));
		assertEquals(new Integer(500), list2.getFirst());
		assertEquals(new Integer(500), list2.get(0));
		assertEquals(new Integer(3), list2.get(3));
		assertEquals(4, list2.size());
	}
	
	@Test(timeout = 5000) 
	public void testRemoveAt() {
		list2.insertFirst(new Integer(1000));
		assertEquals(3, list2.size());
		assertEquals(list2.removeAt(1), new Integer(1));
		assertEquals(list2.removeAt(1), new Integer(2));
		assertEquals(list2.removeAt(0), new Integer(1000));
		assertEquals(0, list2.size());
	}
	
	@Test(timeout = 5000)
	public void testRemove() {
		list2.insertFirst(new Integer(1000));
		assertTrue(list2.remove(new Integer(2)));
		assertTrue(list2.remove(new Integer(1000)));
		assertFalse(list2.remove(new Integer(500)));
		assertTrue(list2.remove(new Integer(1)));
		assertFalse(list2.remove(new Integer(-20320)));
		assertEquals(0, list2.size());
		list2.insertFirst(new Integer(-123));
		assertFalse(list2.remove(new Integer(123)));
	}
	
	@Test(timeout = 5000)
	public void testIndexOf() {
		assertEquals(new Integer(0), new Integer(list2.indexOf(1)));
		assertEquals(new Integer(1), new Integer(list2.indexOf(2)));
		list2.insertLast(new Integer(1250));
		assertEquals(new Integer(2), new Integer(list2.indexOf(1250)));
	}
	
	@Test(timeout = 5000)
	public void testEverything() {
		assertEquals(2, list2.size());
		assertEquals(new Integer(1), list2.removeFirst());
		assertEquals(1, list2.size());
		assertEquals(new Integer(2), list2.removeFirst());
		assertEquals(0, list2.size());
		list2.insertFirst(new Integer(2));
		list2.insertFirst(new Integer(1));
		list2.insertAt(2, new Integer(3));
		assertEquals(3, list2.size());
		assertEquals(new Integer(3), list2.getLast());
		list2.insertAt(0, new Integer(500));
		assertEquals(new Integer(500), list2.getFirst());
		assertEquals(new Integer(500), list2.get(0));
		assertEquals(new Integer(3), list2.get(3));
		assertEquals(4, list2.size());
		Iterator<Integer> iterator = list2.iterator(); 
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Integer(500));
		assertEquals(iterator.next(), new Integer(1));
		assertTrue(list2.remove(new Integer(500)));
		assertEquals(new Integer(1), new Integer(list2.indexOf(iterator.next())));
	}
}
