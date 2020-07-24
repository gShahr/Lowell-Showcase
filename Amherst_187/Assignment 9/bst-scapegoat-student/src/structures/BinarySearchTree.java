package structures;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;
	boolean contains;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		containsHelper(root, t);
		if (contains == true) {
			contains = false;
			return true;
		} else {
			return false;
		}
	}
	
	// preorder traversal to see if tree contains value T
	private void containsHelper(BSTNode<T> node, T t) {
		if (node != null) {
			if (node.getData().equals(t)) {
				contains = true;
			}
			containsHelper(node.getLeft(), t);
			containsHelper(node.getRight(), t);
		}
	}

	public boolean remove(T t) {
		boolean res = contains(t);
		if (res) {
			root = removeFromSubtree(root, t);
		}
		return res;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
		} else { // found node
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
			}
		}
		return node;
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		if (contains(t)) {
			return t;
		} else {
			return null;
		}
	}

	public void add(T t) {
		root = addToSubtree(t, root);
	}

	// inserts element into the subtree and returns root
	protected BSTNode<T> addToSubtree(T t, BSTNode<T> node) {
		if (node == null) {
			return new BSTNode<T>(t, null, null);
		}
		if (t.compareTo(node.getData()) <= 0) {
			node.setLeft(addToSubtree(t, node.getLeft()));
		} else {
			node.setRight(addToSubtree(t, node.getRight()));
		}
		return node;
	}
	
	// inserts element into the subtree and returns the node that was inserted
	protected BSTNode<T> addToSubtree2(T t) {
		BSTNode<T> child = new BSTNode<T>(t, null, null);
		BSTNode<T> curr = root;
		BSTNode<T> prev = null;
		while (curr != null) {
			prev = curr;
			if (t.compareTo(curr.getData()) <= 0) {
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		if (prev == null) {
			prev = child;
			root = prev;
		} else if (t.compareTo(prev.getData()) <= 0) {
			prev.setLeft(child);
		} else {
			prev.setRight(child);
		}
		return prev;
	}

	@Override
	/** Tried using recursion for min/max and realized that I had to create extra space 
	    because the method will keep returning a value for each scope or time you call the method once it reaches the base case,
	    which will result in ending up with the root node instead of the min/max value because each scope returns the current's node value.
	    As a result, iteration would be best since it only will return one value when it reaches 
	    the left most node)
	    If we used recursion, we would have to store all the values and then use the first element
	    This would waste space and not be optimal **/
	public T getMinimum() {
		if (root == null) {
			return null;
		} else {
			return leftMost(root);
		}
	}
	
	private T leftMost(BSTNode<T> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		} 
		return node.getData();
	}
	
	
	@Override
	public T getMaximum() {
		if (root == null) {
			return null;
		} else {
			return rightMost(root);
		}
	}
	
	private T rightMost(BSTNode<T> node) {
		while (node.getRight() != null) {
			node = node.getRight();
		} 
		return node.getData();
	}


	@Override
	public int height() {
		if (isEmpty()) {
			return -1;
		} else {
			return heightHelper(root);
		}
	}
	
	protected int heightHelper(BSTNode<T> node) {
		if (node != null) {
			return Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight())) + 1;
		}
		return -1;
	}

	@Override
	public Iterator<T> preorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}
	
	public Iterator<T> inorderIterator(BSTNode<T> node) {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, node);
		return queue.iterator();
	}
	
	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> postorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		return equalsHelper(root, other.getRoot());
		/**Iterator<T> q1 = postorderIterator();
		Iterator<T> q2 = other.postorderIterator();
		while (q1.hasNext() && q2.hasNext()) {
			if (!q1.next().equals(q2.next())) {
				return false;
			}
		}
		if (q1.hasNext() || q2.hasNext()) {
			return false;
		} else {
			return true; 
		} **/
	}
	
	public boolean equalsHelper(BSTNode<T> node1, BSTNode<T> node2) {
		if (node1 != null && node2 != null) {
			if (!node1.getData().equals(node2.getData())) {
				return false;
			} else {
				return equalsHelper(node1.getLeft(), node2.getLeft()) && equalsHelper(node1.getRight(), node2.getRight());
			}
		} else if (node1 == null && node2 == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// compare inorder traverals -> taking advtange of how a binary search tree is set up
		Iterator<T> q1 = inorderIterator();
		Iterator<T> q2 = other.inorderIterator();
		while (q1.hasNext() && q2.hasNext()) {
			if (!q1.next().equals(q2.next())) {
				return false;
			}
		}
		if (q1.hasNext() || q2.hasNext()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isBalanced() {
		return isBalancedHelper(root);
	}
	
	public boolean isBalancedHelper(BSTNode<T> node) {
		if (node != null) { // As long as the node is not null, we have to keep checking the subtrees until we get to the leaves
			return Math.abs(heightHelper(node.getLeft()) - heightHelper(node.getRight())) <= 1 && isBalancedHelper(node.getLeft()) && isBalancedHelper(node.getRight());
		} else {
			return true; // if the root is empty, then the tree is balanced (base case)
		}
	}

	@Override
	// Make an in order queue of the current tree 
	// Then make the value in the middle (the one closer to the end if there is an even amount) the new root and add the remaining nodes to the tree
	// Realized that I have to keep recursing through and not simply use the add function otherwise I'll still be left with an unbalanced tree
	// Have to divide and conqeuer and keep using the middle value setting it to sub roots
	public void balance() {
		/**
		Iterator<T> iterator = inorderIterator();
		int mid = size()/2;
		Queue<T> q = new LinkedList<T>();
		while (mid-- > 0) {
			q.add(iterator.next());
		}
		T newRoot = iterator.next();
		iterator.remove();
		root = new BSTNode<T>(newRoot, null, null);
		while (iterator.hasNext()) {
			add(iterator.next());
		}
		while (!q.isEmpty()) {
			add(q.poll());
		} **/
		T[] array = (T[]) new Comparable[size()];
		Iterator<T> iterator = inorderIterator();
		for (int i = 0; i < size(); i++) {
			array[i] = iterator.next();
		}
		root = balanceHelper(array, 0, array.length-1);
	}
	
	public BSTNode<T> balanceHelper(T[] array, int start, int end) {
		if (start > end) {
			return null;
		} else {
			int mid = (start + end) / 2;
			BSTNode<T> node = new BSTNode<T>(array[mid], null, null);
			node.setLeft(balanceHelper(array, start, mid-1));
			node.setRight(balanceHelper(array, mid+1, end));
			return node;
		}
	}
	
	@Override
	public BSTNode<T> getRoot() {
		// DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// DO NOT MODIFY
		// see project description for explanation

		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}
	
	// returns the parent of a node
	public BSTNode<T> getParent(T value) {
		BSTNode<T> node = root;
		if (node == null) {
			return null;
		} else {
			BSTNode<T> getParent = null;
			while (node != null) {
				if (value.compareTo(node.getData()) < 0) {
					getParent = node;
					node = node.getLeft();
				} else if (value.compareTo(node.getData()) > 0) {
					getParent = node;
					node = node.getRight();
				} else {
					break;
				}
			}
			return getParent;
		}
	}
}