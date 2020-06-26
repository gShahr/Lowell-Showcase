package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {	
	
	Queue<File> queue;
	Queue<File> track;
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if (!rootNode.exists()) {
			throw new FileNotFoundException();
		} else {
			// list all the files -> sort -> enqueue -> for each added file rinse and repeat
			queue = new Queue<File>();
			track = new Queue<File>();
			queue.enqueue(rootNode);
			track.enqueue(rootNode);
			while (!track.isEmpty()) {
				int size = track.size();
				while (size-- > 0) {
					File data = track.dequeue();
					if (data.isDirectory()) {
						File[] dataChild = data.listFiles();
						Arrays.sort(dataChild);
						for (File f: dataChild) {
							track.enqueue(f);
							queue.enqueue(f);
						}
					}
				}
			}
		}
	}
	
	
	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
		return queue.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
