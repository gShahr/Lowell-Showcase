package stack;

public class ResizingArrayStack<T> implements Stack<T> {
    private T[] stack;
    private int size;
    
    /**
     * Creates a new ResizingArrayStack.
     * @param initialCapacity the initial length of the backing array for the
     *        stack
     */
    public ResizingArrayStack(int initialCapacity) {
    	stack = (T[]) new Object[initialCapacity];
    	size = 0;
    }

    @Override
    public T pop() throws StackUnderflowException {
    	// create a new array to remove old value
    	if (isEmpty()) {
    		throw new StackUnderflowException();
    	} else {
    		T element = stack[size-1];
    		stack[size-1] = null;
    		size--;
    		if (size < stack.length/2) {
    			T[] temp = (T[]) new Object[stack.length/2];
    			for (int i = 0; i < size; i++) {
    				temp[i] = stack[i];
    			}
    			stack = temp;
    		}
    		return element;
    	}
    }

    @Override
    public T peek() throws StackUnderflowException {
    	if (isEmpty()) {
    		throw new StackUnderflowException();
    	} else {
    		return stack[size-1];
    	}
    }

    @Override
    public void push(T element) {
    	if (size >= stack.length) {
    		T[] temp = (T[]) new Object[stack.length * 2];
    		for (int i = 0; i < stack.length; i++) {
    			temp[i] = stack[i];
    		}
    		temp[stack.length] = element; 
    		stack = temp;
    	} else {
    		stack[size] = element;
    	}
    	size++;
    }

    @Override
    public boolean isEmpty() {
    	return size() == 0;
    }

    @Override
    public int size() {
    	return size;
    }

    @Override
    public int capacity() {
    	return stack.length;
    }
}
