package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	
	LLNode<T> head;
	int size;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	// deletes and returns top most element 
	// 3 different actions -> if list is empty, deleting head node, deleting tail node
	// -> we have to set the node prior to the tail to null because a node is pointing to it (it will not work if we set the tail to null)
	//    -> unlike a head node, that is why we can just set head to null because there are no other nodes pointing to it
	// As a result, we need 2 different algorithms for deleting a head node and a tail node
	public T pop() throws StackUnderflowException {
		// if list is empty, then it throws an exception
		// -> otherwise, it iterates through the list until it reaches the last element 
		// ->-> stores its value and then deletes it by setting it equal to null and then returns the saved value
		if (head == null) { // throws exception if list is empty
			throw new StackUnderflowException();
		} else if (head.getNext() == null) { // set the head node to null if there is only one node in the list
			T data = head.getData();
			size--;
			head = null;
			return data;
		} else { // delete the tail node otherwise
			LLNode<T> temp = head;
			LLNode<T> prev = null;
			while (temp.getNext() != null) {
				prev = temp;
				temp = temp.getNext();
			}
			T data = temp.getData(); // save value so we don't lose it when we set the last node to null
			prev.setNext(null); // set node to null to delete it from the list -> don't have to worry about edges or links because it is always the tail node
			size--; // decrease size by one since we are removing a node
			return data;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// returns the top most node's data value
	public T top() throws StackUnderflowException {
		if (head == null) {
			throw new StackUnderflowException();
		} else {
			// iterate through the list until we reach the tail node
			LLNode<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			return temp.getData(); // return the tail node's value
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// returns if the list is empty or not
	public boolean isEmpty() {
		// if the head of the list is null, then the list is empty -> vice versa if the head is not null
		return head == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// returns the size of the list
	public int size() {
		// return the attribute for this class
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// adds an element to the list by creating a new node with the given data point and appending it to the list
	public void push(T elem) {
		// iterate through until we reach the old tail
		// -> set the tail's next node to a new one created with the given element
		if (head == null) { // if the head is null -> we have to create a new list 
			head = new LLNode<T>(elem);
		} else {            // otherwise, we iterate through the list until we get to the tail node
			LLNode<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			LLNode<T> node = new LLNode<T>(elem);
			temp.setNext(node);
		}
		size++; // increment size by one since we are adding a node to the list
	}

}
