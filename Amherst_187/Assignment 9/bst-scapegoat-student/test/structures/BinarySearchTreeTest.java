package structures;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

public class BinarySearchTreeTest {

	private BSTInterface<Integer> emptyTree;
	private BSTInterface<String> oneNodeTree;
	private BSTInterface<Integer> simpleTree;
	private BSTInterface<Integer> complexTree;
	private static final String FOO = "foo";

	@Rule
	public Timeout timeout = new Timeout(1000);
	
	@Before
	public void before() {
		emptyTree = new BinarySearchTree<Integer>();
		oneNodeTree = new BinarySearchTree<String>();
		simpleTree = new BinarySearchTree<Integer>();
		complexTree = new BinarySearchTree<Integer>();
		oneNodeTree.add(FOO);
		for (int i = 0; i < 1000; i++) {
			complexTree.add(i);
			complexTree.add(i * -1);
		}
		for (int i = 0; i < 10; i++) {
			simpleTree.add(i);
		}
	}
	
	@Test
	public void testEmpty() {
		assertTrue(emptyTree.isEmpty());
		assertFalse(complexTree.isEmpty());
	}

	@Test
	public void testNotEmpty() {
		assertFalse(oneNodeTree.isEmpty());
		assertTrue(emptyTree.isEmpty());
		assertFalse(complexTree.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyTree.size());
		assertEquals(1, oneNodeTree.size());
		oneNodeTree.add("Video");
		assertEquals(2, oneNodeTree.size());
		oneNodeTree.add("Game");
		assertEquals(3, oneNodeTree.size());
		assertEquals(2000, complexTree.size());
	}
	
	@Test
	public void testContains() {
		assertTrue(oneNodeTree.contains(FOO));
		assertFalse(oneNodeTree.contains("GAMES"));
		oneNodeTree.add("VIDEO");
		assertFalse(oneNodeTree.contains("Video"));
		assertTrue(oneNodeTree.contains("VIDEO"));
	}
	
	@Test
	public void testRemove() {
		assertFalse(emptyTree.remove(0));
		assertFalse(emptyTree.remove(1000));
		assertTrue(complexTree.remove(999));
	}
	
	@Test
	public void testGet() {
		assertEquals(FOO, oneNodeTree.get(FOO));
	}
	
	@Test
	public void testAdd() {
		emptyTree.add(1);
		assertFalse(emptyTree.isEmpty());
		assertEquals(1, emptyTree.size());
	}
	
	@Test
	public void testGetMinimum() {
		assertEquals(null, emptyTree.getMinimum());
		assertEquals(new Integer(-999), complexTree.getMinimum());
	}

	@Test
	public void testGetMaximum() {
		assertEquals(FOO, oneNodeTree.getMaximum());
		assertEquals(new Integer(999), complexTree.getMaximum());
	}

	@Test
	public void testHeight() {
		assertEquals(-1, emptyTree.height());
		assertEquals(0, oneNodeTree.height());
		assertEquals(1000, complexTree.height());
	}
	
	@Test
	public void testPreorderIterator() {
		Iterator<String> i = oneNodeTree.preorderIterator();
		while (i.hasNext()) {
			assertEquals(FOO, i.next());			
		}
	}

	@Test
	public void testInorderIterator() {
		Iterator<String> i = oneNodeTree.inorderIterator();
		while (i.hasNext()) {
			assertEquals(FOO, i.next());			
		}
	}

	@Test
	public void testPostorderIterator() {
		Iterator<Integer> i = emptyTree.postorderIterator();
		assertFalse(i.hasNext());
	}
	
	@Test
	public void testEquals() {
		BSTInterface<String> tree = new BinarySearchTree<String>();
		assertFalse(oneNodeTree.equals(tree));
		tree.add(new String("foo"));
		assertTrue(oneNodeTree.equals(tree));
		oneNodeTree.add("video");
		tree.add("video");
		assertTrue(oneNodeTree.equals(tree));
		tree.add("game");
		assertFalse(oneNodeTree.equals(tree));
		oneNodeTree.add("game");
		assertTrue(oneNodeTree.equals(tree));
	}
	
	@Test
	public void testSameValues() {
		BSTInterface<Integer> tree = new BinarySearchTree<Integer>();
		assertTrue(emptyTree.sameValues(tree));
		
		emptyTree.add(-505);
		emptyTree.add(1);
		emptyTree.add(2);
		emptyTree.add(3);
		
		tree.add(3);
		tree.add(2);
		tree.add(1);
		tree.add(-505);
		
		assertTrue(emptyTree.sameValues(tree));
	}
	
	@Test 
	public void testIsBalanced() {
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(1);
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(2);
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(3);
		assertFalse(emptyTree.isBalanced());
		assertFalse(complexTree.isBalanced());
		
	}
	
	@Test 
	public void testBalance() {
		emptyTree.add(1);
		emptyTree.add(2);
		emptyTree.add(3);
		assertFalse(emptyTree.isBalanced());
		emptyTree.balance();
		assertTrue(emptyTree.isBalanced());
		assertFalse(complexTree.isBalanced());
		complexTree.balance();
		assertTrue(complexTree.isBalanced());
		assertEquals(new Integer(complexTree.size()), new Integer(2000));
		assertEquals(new Integer(complexTree.height()), new Integer(10));
		simpleTree.balance();
		assertEquals(new Integer(simpleTree.height()), new Integer(3));
		assertTrue(simpleTree.isBalanced());
	}
}