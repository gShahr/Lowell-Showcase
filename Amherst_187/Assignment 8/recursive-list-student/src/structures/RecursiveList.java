package structures;

import java.util.Iterator;

/**
 * A {@link ListInterface} is a container that supports insertion, removal, and
 * searching.
 * 
 * @author jcollard
 * 
 * @param <T>
 */
public class RecursiveList<T> implements ListInterface<T> {
	
	// Have to create instance Nodes head and Temp to keep track of where we are
	private Node<T> head, temp; // temp to keep track of where we are in the list -> we can't define it in a method because it will reset the value each recursive call
	private int size;
	
	// default constructor
	public RecursiveList() {
	}
	
	
	/**
	 * Returns the number of elements in this {@link ListInterface}. This method
	 * runs in O(1) time.
	 * 
	 * @return the number of elements in this {@link ListInterface}
	 */
	public int size() {
		return size;
	}
	/**
	 * Adds an element to the front of this {@link ListInterface}. This method
	 * runs in O(1) time. For convenience, this method returns the
	 * {@link ListInterface} that was modified.
	 * 
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return The modified {@link ListInterface}
	 */
	public ListInterface<T> insertFirst(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException();
		} else if (head == null) {
			head = new Node<T>(elem, null);
			temp = head;
			size++;
			return this;
		} else {
			head = new Node<T>(elem, head);
			temp = head;
			size++;
			return this;
		}
	}

	/**
	 * Adds an element to the end of this {@link ListInterface}. This method
	 * runs in O(size) time. For convenience, this method returns the
	 * {@link ListInterface} that was modified.
	 * 
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return the modified {@link ListInterface}
	 */
	public ListInterface<T> insertLast(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException();
		} 
		if (head == null) {
			head = new Node<T>(elem, null);
			temp = head;
			size++;
			return this;
		} else if (temp.getNext() == null) {
			Node<T> newTail = new Node<T>(elem, null);
			temp.setNext(newTail);
			temp = head; 
			size++;
			return this;
		} else {
			temp = temp.getNext();
			return insertLast(elem);
		}
	}

	/**
	 * Adds an element at the specified index such that a subsequent call to
	 * {@link ListInterface#get(int)} at {@code index} will return the inserted
	 * value. This method runs in O(index) time. For convenience, this method
	 * returns the {@link ListInterface} that was modified.
	 * 
	 * @param index
	 *            the index to add the element at
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0 or greater than
	 *             {@link ListInterface#size()}
	 * @return The modified {@link ListInterface}
	 */
	public ListInterface<T> insertAt(int index, T elem) {
		// if we want to insert something in the body of the linked list, we want to stop one before the index we are inserting into -> As a result, the base case is 1
		// -> This allows us to change the pointers of the new node we want to insert and the current node's pointer that we want to change
		// if index is 0, then we know that it always changes the first node
		if (elem == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) { // need 3 seperate base cases depending on where you are inserting element (head, body, or tail)
			temp = head;
			return insertFirst(elem);
		} else if (index == 1 && temp.getNext() == null) {
			temp = head;
			return insertLast(elem);
		} else if (index == 1) {
			Node<T> node = new Node<T>(elem, temp.getNext());
			temp.setNext(node);
			temp = head;
			size++;
			return this;
		} else {
			temp = temp.getNext();
			return insertAt(index-1, elem);
		}
	}
	/**
	 * Removes the first element from this {@link ListInterface} and returns it.
	 * This method runs in O(1) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the removed element
	 */
	public T removeFirst() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else {
			Node<T> temp = head.getNext();
			T data = head.getData(); // store old head data before we delete it
			head.setNext(null);
			head = temp;
			this.temp = head;
			size--;
			return data;
		}
	}

	/**
	 * <p>
	 * Removes the last element from this {@link ListInterface} and returns it.
	 * This method runs in O(size) time.
	 *</p>
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the removed element
	 */
	public T removeLast() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else {
			if (head.getNext() == null) {
				T data = head.getData();
				head = null;
				temp = head;
				size--;
				return data;
			} else if (temp.getNext().getNext() == null) {
				T data = temp.getNext().getData();
				temp.setNext(null);
				temp = head;
				size--;
				return data;
			} else {
				temp = temp.getNext();
				return removeLast();
			}
			
		}
	}

	/**
	 * Removes the ith element in this {@link ListInterface} and returns it.
	 * This method runs in O(i) time.
	 * 
	 * @param i
	 *            the index of the element to remove
	 * @throws IndexOutOfBoundsException
	 *             if {@code i} is less than 0 or {@code i} is greater than or
	 *             equal to {@link ListInterface#size()}
	 * @return The removed element
	 */
	public T removeAt(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException();
		} else if (i == 0) { // need 3 seperate base cases depending on where you are inserting element (head, body, or tail)
			temp = head;
			return removeFirst();
		} else if (i == 1 && temp.getNext() == null) {
			temp = head;
			return removeLast();
		} else if (i == 1) {
			Node<T> temp2 = temp.getNext();
			temp.setNext(temp.getNext().getNext());
			temp2.setNext(null);
			temp = head;
			size--;
			return temp2.getData();
		} else {
			temp = temp.getNext();
			return removeAt(i-1);
		}
	}

	/**
	 * Returns the first element in this {@link ListInterface}. This method runs
	 * in O(1) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the first element in this {@link ListInterface}.
	 */
	public T getFirst() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else {
			return head.getData();
		}
	}

	/**
	 * Returns the last element in this {@link ListInterface}. This method runs
	 * in O(size) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the last element in this {@link ListInterface}.
	 */
	public T getLast() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else {
			if (temp.getNext() == null) {
				T data = temp.getData();
				temp = head;
				return data;
			} else {
				temp = temp.getNext();
				return getLast();
			}
			
		}
	}

	/**
	 * Returns the ith element in this {@link ListInterface}. This method runs
	 * in O(i) time.
	 * 
	 * @param i
	 *            the index to lookup
	 * @throws IndexOutOfBoundsException
	 *             if {@code i} is less than 0 or {@code i} is greater than or
	 *             equal to {@link ListInterface#size()}
	 * @return the ith element in this {@link ListInterface}.
	 */
	public T get(int i) throws IndexOutOfBoundsException {
		// we don't need 3 separate base cases for getting the data from a node because we are not inserting anything, just searching at a given index
		// -> As a result, we only have 1 base case
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		} else if (i == 0) {     
			T data = temp.getData();
			temp = head;
			return data;
		} else {
			temp = temp.getNext();
			return get(i-1);
		}
	}
	/**
	 * Removes {@code elem} from this {@link ListInterface} if it exists. If
	 * multiple instances of {@code elem} exist in this {@link ListInterface}
	 * the one associated with the smallest index is removed. This method runs
	 * in O(size) time.
	 * 
	 * @param elem
	 *            the element to remove
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return {@code true} if this {@link ListInterface} was altered and
	 *         {@code false} otherwise.
	 */
	public boolean remove(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException();
		} else {
			int index = removeHelper(elem, 0);
			if (index == -1) {
				return false;
			} else {
				removeAt(index);
				return true;
			}
		}
	}
	
	public int removeHelper(T toFind, int current) {
		if (isEmpty()) { // if the list is empty, then return -1 since there is nothing to find
			return -1;
		} else if (temp.getData().equals(toFind)) { // if we find a match, return the index of the node we need to remove
			temp = head;
			return current;
		} else if (current+1 == size) { // if the counter reaches the size of the list, then the element doesn't exist within the list
			temp = head;
			return -1;
		} else { // keep going through all node values until we reach end of the list
			temp = temp.getNext();
			return removeHelper(toFind, current + 1);
		}
	}

	/**
	 * Returns the smallest index which contains {@code elem}. If there is no
	 * instance of {@code elem} in this {@link ListInterface} then -1 is
	 * returned. This method runs in O(size) time.
	 * 
	 * @param elem
	 *            the element to search for
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return the smallest index which contains {@code elem} or -1 if
	 *         {@code elem} is not in this {@link ListInterface}
	 */
	public int indexOf(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		} else {
			int index = indexHelper(elem, 0);
			if (index == -1) {
				return -1;
			} else {
				return index;
			}
		}
	}
	
	public int indexHelper(T toFind, int current) {
		if (isEmpty()) { // if the list is empty, then return -1 since there is nothing to find
			return -1;
		} else if (temp.getData().equals(toFind)) { // if we find a match, return the index of the node we need to remove
			temp = head;
			return current;
		} else if (current+1 == size) { // if the counter reaches the size of the list, then the element doesn't exist within the list
			temp = head;
			return -1;
		} else { // keep going through all node values until we reach end of the list
			temp = temp.getNext();
			return indexHelper(toFind, current + 1);
		}
	}

	/**
	 * Returns {@code true} if this {@link ListInterface} contains no elements
	 * and {@code false} otherwise. This method runs in O(1) time.
	 * 
	 * @return {@code true} if this {@link ListInterface} contains no elements
	 *         and {@code false} otherwise.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public Iterator<T> iterator() {
		return new NodeIterator<T>(head);
	}

}
