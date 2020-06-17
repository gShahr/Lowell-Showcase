package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;  

  
  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // iterate through the linked list while incrementing count until it reaches a node that is null 
    int count = 0;
    LinkedNode<E> temp = head; // create a temp value for head of linked list so we don't lose our data
    while (temp != null) {
    	temp = temp.getNext();
    	count++; 
    }
    return count;
  }

  @Override
  public boolean isEmpty() {
    // If there is no starting point, then the list is empty
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  // checks to see if an object is in the linked list
  public boolean contains(Object o) {
    LinkedNode<E> temp = head; // temp value to store head of linked list
    while (temp != null) {
    	//System.out.println(o + " " + temp.getData());
    	if (temp.getData() == o) {
    		return true;
    	} else {
    		temp = temp.getNext();
    	}
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // naive approach taken
	LinkedNode<E> temp = head;
	int count = 0;
	for (int i = 0; i < size(); i++) {
		if (that.contains(temp.getData())) {
			count++;
		}
		temp = temp.getNext();
	}
	if (count == size()) {
		return true;
	} else {
		return false;
	}
  }

  @Override
  public boolean isSuperset(Set<E> that) { 
	// 'has a relationship' -> if this is a superset, then that is a subset
	return that.isSubset(new LinkedSet<E>(head));
  }

  @Override
  // adds an element to the set (No duplicates) -> since sets are immutable, we must return a new set each time
  public Set<E> adjoin(E e) {   
    if (!contains(e)) {
    	// create a new node which link or edge refers to the old head of the set
    	// -> As a result, we must reassign the head value to this node so that we iterate over it along with the rest of the list
    	// We must do this because our node's attributes are private and there are no setters for changing it
    	LinkedNode<E> node = new LinkedNode<E>(e, head);
    	return new LinkedSet<E>(node); // can't do head = node because these sets have to be immutable -> if you change the current's head value, you are making the set mutable
    }
    //System.out.println(head.getData()); //-> debugging to see if method works
    return new LinkedSet<E>(head);
  }

  @Override
  // returns a new set that has shared elements between both sets
  public Set<E> union(Set<E> that) {
	Set<E> set = that;
	LinkedNode<E> temp = head;
    while (temp != null) {
    	set = set.adjoin(temp.getData());
    	temp = temp.getNext();
    }
    return set;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
	Set<E> set = new LinkedSet<E>();
	LinkedNode<E> temp = head;
    while (temp != null) {
    	if (that.contains(temp.getData())) {
    		set = set.adjoin(temp.getData());
    	}
    	temp = temp.getNext();
    }
	return set;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
	Set<E> set = new LinkedSet<E>();  
	LinkedNode<E> temp = head;
	while (temp != null) {
		if (!that.contains(temp.getData())) {
			System.out.println(temp.getData());
			set = set.adjoin(temp.getData());
		}
		temp = temp.getNext();
	}
	return set;
  }

  @Override
  public Set<E> remove(E e) {
	Set<E> set = new LinkedSet<E>();
	LinkedNode<E> temp = head;
	while (temp != null) {
		if (temp.getData() != e) {
			set = set.adjoin(temp.getData());
		} 
		temp = temp.getNext();
	}
	return set;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
