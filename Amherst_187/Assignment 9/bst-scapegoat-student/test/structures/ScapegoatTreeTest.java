package structures;

import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

public class ScapegoatTreeTest {

	private BSTInterface<Integer> tree;

	@Rule
	public Timeout timeout = new Timeout(2000); // initially 1000
	
	@Before
	public void before() {
		tree = new ScapegoatTree<Integer>();
	}
	
	@Test
	public void testAdd() {
		tree.add(0);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		assertEquals(3, tree.height());
		tree.add(4);
		assertEquals(3, tree.height());
	}
	
	@Test
	public void testAdd2() {
		tree.add(7);
		tree.add(6);
		tree.add(3);
		tree.add(1);
		tree.add(0);
		tree.add(8);
		tree.add(9);
		tree.add(4);
		tree.add(5);
		tree.add(2);
		assertEquals(5, tree.height());
	}
	
	@Test 
	public void testRemove() {
		for (int i: new int[] {3, 1, 5, 0, 4, 2, 6} ) {
			tree.add(i);
		}

		for (int i: new int[] {1, 2, 0, 4}) {
			tree.remove(i);
		}
		
		BSTInterface<Integer> smallTree = new BinarySearchTree<Integer>();
		smallTree.add(5);
		smallTree.add(3);
		smallTree.add(6);
		
		assertTrue(tree.equals(smallTree));
	}
	
	@Test 
	public void testBigNumbers() {
		for (int i = 0; i < 1000; i++) {
			tree.add(i);
		}
				
		
		BSTInterface<Integer> smallTree = new ScapegoatTree<Integer>();
		for (int i = 0; i < 1000; i++) {
			smallTree.add(i);
		}
		
		assertTrue(tree.equals(smallTree));
		
		tree.remove(999);
		smallTree.remove(999);
		
		assertTrue(tree.equals(smallTree));
	}
}
