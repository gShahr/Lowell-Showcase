package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {

	private int upperBound;
	
	/**
	 * Adds an element to the tree.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * In addition to obeying the BST rule, the resulting tree must also obey
	 * the scapegoat rule. 
	 * 
	 * This method must only perform rebalancing of subtrees when indicated
	 * by the scapegoat rule; do not unconditionally call balance() 
	 * after adding, or you will receive no credit. 
	 * See the project writeup for details.
	 * 
	 * @param element
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(T element) {
		upperBound++;
		BSTNode<T> u = addToSubtree2(element);
		// If adding the node breaks the second rule, rebalance the tree
		if (Math.log(upperBound) / Math.log(1.5) < height()) {
			BSTNode<T> w = getParent(u.getData());
			while (3 * subtreeSize(w) <= 2 * subtreeSize(getParent(w.getData()))) {
				w = getParent(w.getData());
			}
			/**
			T[] array = (T[]) new Comparable[subtreeSize(w)];
			Iterator<T> iterator = inorderIterator(w);
			for (int i = 0; i < subtreeSize(w); i++) {
				array[i] = iterator.next();
			} 
			BSTNode<T> node = balanceHelper(array, 0, array.length-1); 
			if (getParent(w.getData()).getData().compareTo(node.getData()) > 0) {
				getParent(w.getData()).setLeft(node);
			} else {
				getParent(w.getData()).setRight(node);
			} **/
			ScapegoatTree<T> sub = new ScapegoatTree<T>();
			sub.root = w;
			BSTNode<T> org = getParent(w.getData());
			sub.balance();
			if (getParent(w.getData()).getData().compareTo(sub.root.getData()) > 0) {
				org.setLeft(sub.root);
			} else {
				org.setRight(sub.root);
			}
			
		} 
	}
	
	/**
	 * Attempts to remove one copy of an element from the tree, returning true
	 * if and only if such a copy was found and removed.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * In addition to obeying the BST rule, the resulting tree must also obey
	 * the scapegoat rule.
	 * 
	 * This method must only perform rebalancing of subtrees when indicated
	 * by the scapegoat rule; do not unconditionally call balance() 
	 * after removing, or you will receive no credit. 
	 * See the project writeup for details.

	 * @param element
	 * @return true if and only if an element removed
	 * @throws NullPointerException if element is null
	 */
	@Override
	public boolean remove(T element) {
		if (element == null) {
			throw new NullPointerException();
		} else {
			boolean res = super.remove(element);
			if (upperBound > 2 * size()) {
				balance();
				upperBound = size();
			}
			return res;
		}
	}
	
}
