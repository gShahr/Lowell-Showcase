package sorting;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import structures.Queue;

public class MergeSorterTest {
	private MergeSorter<Integer> ms = new MergeSorter<Integer>();
	private Queue<Integer> empty;
	private Queue<Integer> one;
	private Queue<Integer> unsorted;
	private Queue<Integer> output1;
	private Queue<Integer> output2;

	@Before
	public void before() {
		empty = new Queue<Integer>();
		one = new Queue<Integer>();

		output1 = new Queue<Integer>();		
		output2 = new Queue<Integer>();

		one.enqueue(1);
		unsorted = new Queue<Integer>();
		for (int i : new int[] {8, 4, 0, 3, 6, 1, 7, 9, 5, 2}) {
			unsorted.enqueue(i);
		}
	}
	
	@Test
	public void testDivideEmpty() throws Exception {
		ms.divide(empty, output1, output2);
		assertTrue(empty.isEmpty());
		assertTrue(output1.isEmpty());
		assertTrue(output2.isEmpty());
	}
	
	@Test
	public void testDivideOne() throws Exception {
		ms.divide(one, output1, output2);
		assertTrue(one.isEmpty());
		assertTrue(output1.isEmpty() || output2.isEmpty());
		assertFalse(output1.isEmpty() && output2.isEmpty());
	}

	@Test
	public void testDivideNSplitsEvenly() throws Exception {
		Queue<Integer> q = new Queue<Integer>();
		for (int i = 0; i < 11; i++) {
			q.enqueue(i);
		}
		ms.divide(q, output1, output2);
		assertTrue(((output1.size() == 5) && (output2.size() == 6)) ||
				   ((output1.size() == 6) && (output2.size() == 5)));;
	}

	@Test
	public void testDivideNContainsAll() throws Exception {
		Queue<Integer> q = new Queue<Integer>();
		for (int i = 0; i < 11; i++) {
			q.enqueue(i);
		}
		ms.divide(q, output1, output2);
		int[] t = new int[11];
		int i = 0;
		while (!output1.isEmpty()) {
			t[i] = output1.dequeue();
			i++;
		}
		while (!output2.isEmpty()) {
			t[i] = output2.dequeue();
			i++;
		}
		Arrays.sort(t);
		for (int j = 0; j < 11; j++) {
			assertEquals(j, t[j]);
		}
	}

	@Test
	public void testMergeTwoEmpty() throws Exception {		
		Queue<Integer> result = ms.merge(empty, empty);
		assertTrue(result.isEmpty());
	}
	
	@Test 
	public void testMergeOneAndEmpty() throws Exception {
		Queue<Integer> result = ms.merge(one, empty);
		assertEquals(1, result.size());
		assertEquals(1, (int)result.dequeue());		
	}
	
	public void testMergeEmptyAndOne() throws Exception {
		Queue<Integer> result = ms.merge(empty, one);
		assertEquals(1, result.size());
		assertEquals(1, (int)result.dequeue());		
	}

	@Test 
	public void testMergeOneAndOne() throws Exception {
		Queue<Integer> two = new Queue<Integer>();
		two.enqueue(2);
		Queue<Integer> result = ms.merge(two, one);
		assertEquals(2, result.size());
		assertEquals(1, (int)result.dequeue());
		assertEquals(2, (int)result.dequeue());
	}
	
	@Test
	public void testMergeThreeAndOne() throws Exception {
		Queue<Integer> three = new Queue<Integer>();
		three.enqueue(-1);
		three.enqueue(1);
		three.enqueue(2);
		
		Queue<Integer> result = ms.merge(three, one);
		assertEquals(4, result.size());
		assertEquals(-1, (int)result.dequeue());
		assertEquals(1, (int)result.dequeue());
		assertEquals(1, (int)result.dequeue());
		assertEquals(2, (int)result.dequeue());
	}
	
	@Test
	public void testMergeOneAndThree() throws Exception {
		Queue<Integer> three = new Queue<Integer>();
		three.enqueue(-1);
		three.enqueue(1);
		three.enqueue(2);
		
		Queue<Integer> result = ms.merge(one, three);
		assertEquals(4, result.size());
		assertEquals(-1, (int)result.dequeue());
		assertEquals(1, (int)result.dequeue());
		assertEquals(1, (int)result.dequeue());
		assertEquals(2, (int)result.dequeue());
	}

	@Test
	public void testMergeSortEmpty() throws Exception {
		Queue<Integer> result = ms.mergeSort(empty);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testMergeSortEmptyUnaliased() throws Exception {
		Queue<Integer> result = ms.mergeSort(empty);
		assertTrue(result.isEmpty());
		empty.enqueue(1);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testMergeSort() throws Exception {
		Queue<Integer> sorted = ms.mergeSort(unsorted);
		assertEquals(10, sorted.size());
		for (int i = 0; i < 10; i++) {
			assertEquals(i, (int)sorted.dequeue());
		}
	}
	
	@Test
	public void testMergeSortUnaliased() throws Exception {
		Queue<Integer> sorted = ms.mergeSort(unsorted);
		sorted.dequeue();
		unsorted.enqueue(10);
		assertEquals(9, sorted.size());
		assertEquals(11, unsorted.size());
	}
	
	@Test
	public void testMergeSort2Elements() throws Exception {
		Queue<Integer> two = new Queue<Integer>();
		two.enqueue(2);
		two.enqueue(1);
		
		two = ms.mergeSort(two);
		assertEquals(1, (int) two.dequeue());
		assertEquals(2, (int) two.dequeue());
	}
	
	@Test
	public void testMergeSort4Elements() throws Exception {
		Queue<Integer> four = new Queue<Integer>();
		four.enqueue(400);
		four.enqueue(30);
		four.enqueue(-50);
		four.enqueue(-5000);
		
		four = ms.mergeSort(four);
		assertEquals(-5000, (int) four.dequeue());
		assertEquals(-50, (int) four.dequeue());
		assertEquals(30, (int) four.dequeue());
		assertEquals(400, (int) four.dequeue());
	}
}
