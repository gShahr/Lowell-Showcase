package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	
	Node<T> head;
	Node<T> tail;
	int size;
	
	public Queue() {
	}
	
	public Queue(Queue<T> other) {
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		head = other.head;
		tail = other.tail;
		size = other.size;
	}
	
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T element) {
		if (head == null) {
			Node node = new Node<T>(element, null);
			head = tail = node;
		} else {
			Node<T> newTail = new Node<T>(element, null);
			tail.setNext(newTail);
			tail = newTail;
		}
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		} else {
			size--;
			T data = head.getData();
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
			return data;
		}
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		} else {
			return head.getData();
		}
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		Node<T> prev = null, next = null;
		Node<T> curr = head;
		while (curr != null) {
			next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		Queue<T> queue = new Queue<T>();
		queue.head = curr;
		queue.tail = tail;
		queue.size = size;
		return queue;
	}
}
