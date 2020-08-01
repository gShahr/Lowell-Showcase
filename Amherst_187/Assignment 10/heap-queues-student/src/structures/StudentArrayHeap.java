package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {


  // constructors are not inherited so make call to super class with super();
  protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}

/**
   * Given an index of some "node" returns the index to that "nodes" left
   * child.
   * 
   * @param index
   *            an index in this {@link AbstractArrayHeap}
   * @return the index of the specified "nodes" left child
   * @throws IndexOutOfBoundsException
   *             if {@code index} is less than 0
   */
  protected int getLeftChildOf(int index) {
	  if (index < 0) {
		  throw new IndexOutOfBoundsException();
	  } else {
		  return index*2 + 1; // function came up with trial and error (similar to binary tree function 2k + 1, but I forget where I glanced over that)
	  }
  }

  /**
   * Given an index of some "node" returns the index to that "nodes" right
   * child.
   * 
   * @param index
   *            a "nodes" index
   * @return the index of the specified "nodes" right child
   * @throws IndexOutOfBoundsException
   *             if {@code index} is less than 0
   */
  protected int getRightChildOf(int index) {
	  if (index < 0) {
		  throw new IndexOutOfBoundsException();
	  } else {
		  return index*2 + 2; // add one to the original function to get the right (derived from the first method)
	  }
  }

  /**
   * Given an index of some "node" returns the index to that "nodes" parent.
   * 
   * @param index
   *            a "nodes" index
   * @return the index of the specified "nodes" parent
   * @throws IndexOutOfBoundsException
   *             if {@code index} is less than 1
   */
  protected int getParentOf(int index) {
	  if (index < 1) {
		  throw new IndexOutOfBoundsException();
	  } else {
		  return (index-1)/2; // inverse of first 2 methods -> subtract 1 so that the right value is adjusted correctly then divide
	  }
  }

  /**
   * <p>
   * This results in the entry at the specified index "bubbling up" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * </p>
   * <p>
   * Note: When add is called, an Entry is placed at the end of the internal
   * array and then this method is called on that index.
   * </p>
   * 
   * @param index
   *            the index to bubble up
   */
  protected void bubbleUp(int index) {
	  // call priority instead of value so that we can make it into a min or max heap -> it will be resolved depending on what value we pass to the comparator 
	  // -> it is abstracted for us so that we only care about bubbling up entries within the list that have a higher priority than their parents 
	  int k = index;
	  int p = (k-1)/2; // calling parentOf would cause issues because it would throw an exception -> this will result in us having to set up 2 more if statements to make sure that the value of parent is valid
	  // however, our condition prevents us from calling an invalid parent index because k will be set to 0 whenever p results in an invalid condition, preventing us from iterating further	  
	  // if child has higher priority than parent and the child's index is valid (above 0), swap these 2 elements
	  while (k > 0 && comparator.compare(heap.get(k).getPriority(), (heap.get(p).getPriority())) > 0) {
		  Collections.swap(heap, k, p);
		  k = p;
		  p = (k-1)/2;
	  }
  }

  /**
   * <p>
   * This method results in the entry at the specified index "bubbling down"
   * to a location such that the property of the heap are maintained. This
   * method should run in O(log(size)) time.
   * </p>
   * <p>
   * Note: When remove is called, if there are elements remaining in this
   * {@link AbstractArrayHeap} the bottom most element of the heap is placed
   * at the 0th index and bubbleDown(0) is called.
   * </p>
   * 
   * @param index
   */
  
  // cornell's implementation involves using another method in order to reuse code so that we don't have 2 seperate cases
  // -> combines left and right case into child case and returns the one with the higher priority
  private int smallerChild(int index) {
	  int l = (2*index)+1;
	  int r = (2*index)+2;
	  if (r >= heap.size() || comparator.compare(heap.get(l).getPriority(), (heap.get(r).getPriority())) > 0) {
		  return l;
	  } 
	  return r;
  }
  
  /** I tried implementing only one method to satisfy this with as little code as possible, however I ran into the issue of 
   comparing values when there was only one child to a parent
   -> would have to implement a try catch block to catch the exception or add another if statement 
      when there is no right child
   */
  protected void bubbleDown(int index) {
	  int k = index;
	  int c = smallerChild(k);
	  // if child is valid and parent has lower priority than one of its children, then iterate
	  while (c < heap.size() && comparator.compare(heap.get(k).getPriority(), (heap.get(c).getPriority())) < 0) { 
		  Collections.swap(heap, k, c);
		  k = c;
		  c = smallerChild(k);
	  }
  }
}
