package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


import java.lang.Iterable;

/**
 * A {@link ListInterface} is an unbounded container supporting appending to the
 * end, retrieval at a specified index, and iteration over the entire list.
 * 
 * @author jcollard
 * 
 * @param <T>
 *            the type of element being stored
 */
public class ListImplementation<T> implements ListInterface<T> {
	
	
	Node<T> head, tail;
	int size;
		
	// default constructor -> automatically sets attributes to default values for head and size (which would be null and 0)
	public ListImplementation() {
	}
	
	public ListImplementation(Node<T> head) {
	}
	
	public int size() {
		return size;
	}

	
	public boolean isEmpty() {
		return head == null;
	}

	
	public T get(int n) throws NoSuchElementException {
		// the list is in reverse order -> so traverse through to get the first element and so on
		Node<T> temp = head;
		int inc = n;
		while (inc-- > 0) { 
			temp = temp.getNext();
		}
		if (temp == null || n < 0) {
			throw new NoSuchElementException();
		} else {
			return temp.getData();
		}
	}

	
	public ListInterface<T> append(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		} else if (head == null) {
			head = new Node<T>(elem, null);
			tail = head;
		} else {
			Node<T> node = new Node<T>(elem, null);
			tail.setNext(node);
			tail = node;
		}
		size++;
		return this;
	}
	
	public T pop() {
		size--;
		if (head.getNext() == null) {
			T data = head.getData();
			head = null;
			tail = null;
			return data;
		}
		Node<T> temp = head;
		while (temp.getNext().getNext() != null) {
			temp = temp.getNext();
		}
		T data = tail.getData();
		temp.setNext(null);
		tail = temp;
		return data;
	} 

	
	public Iterator<T> iterator() {
		return new NodeIterator<T>(head);
	}
}