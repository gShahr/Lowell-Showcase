package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

// I don't understand why we would lose this class when it just iterates over 1 node in the list
// -> we can easily do the same thing with incrementing a node through the list and checking if it is null to see where it ends
// I see -> we use it so we can iterate over 'that' set since we don't have access to its attributes
class LinkedNodeIterator<E> implements Iterator<E> {
  // initialize the head so that we have access to the linked list 
  LinkedNode<E> head = null;
  LinkedNode<E> curr; // make a current node so that we don't lose our data / where the list started

  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
    // initialize linkedset's head in this class
	this.head = head;
	curr = head;
  }

  @Override
  public boolean hasNext() {
    // returns true if the next node is not null; false otherwise
	if (curr != null) {
		return true;
	} else {
		return false;
	}
  }

  @Override
  // returns the next node's data value
  public E next() {
	if (hasNext()) {
		LinkedNode<E> prev = curr;
		curr = curr.getNext();
		return prev.getData(); 
	}
    throw new NoSuchElementException();
  }

  // Leave this one alone.
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
