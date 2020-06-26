package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeIterator<T> implements Iterator<T> {
	
	
	Node<T> head = null;
	Node<T> curr = null;
	Node<T> prev = null;
	
	public NodeIterator(Node<T> head) {
		this.head = head;
		curr = head;
		prev = head;
	}
	
	
	@Override
	public boolean hasNext() {
		if (prev == null) {
			return false;
		} else if (prev.getNext() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public T next() {
		if (hasNext()) {
			prev = curr;
			curr = curr.getNext();
			return prev.getData();
		} else {
			throw new NoSuchElementException();
		}
	}
}
