package balancedbrackets;

import stack.ResizingArrayStack;
import stack.StackUnderflowException;

/**
 * BalancedBrackets contains a single public static utility method and should
 * not be instantiated. It is marked as abstract to prevent attempts to
 * instantiate it.
 */
public abstract class BalancedBrackets {
    /**
     * Return true if and only if the string s is well-formed with respect to
     * matching brackets
     *
     * @param s a string to check for well-formedness
     * @return true iff the string is well-formed
     */
    public static boolean isBalanced(String s) {
    	// using .contains() to check if the concatenated characters are valid 
    	String valid = "(){}[]";
    	ResizingArrayStack<Character> stack = new ResizingArrayStack<Character>(1);
    	// iterate through entire string and push open brackets and pop closed brackets
    	// -> if the popped bracket (open bracket) doesn't match its closing bracket, then the string is not balanced
    	for (char c: s.toCharArray()) {
    		// all valid open brackets
        	if (c == '(' || c == '{' || c == '[') {  
        		stack.push(c);
        	} else if (c == ')' || c == '}' || c == ']') {
        		// naive approach taken to reverse the brackets so they can be compared with the popped element
        		// -> if we don't do this, we will always be comparing 2 different elements, which will always result in false
        		char opp;
        		if (c == ')') {
        			opp = '(';
        		} else if (c == '}') {
        			opp = '{';
        		} else {
        			opp = '[';
        		}
        		// if .pop() results in an exception, catch it so we can return false
        		try {
        			// more optimized approach using .contains() to check if the brackets are valid 
        			// -> if valid is false, then the brackets were not of the same type / not valid
        			if (!valid.contains(Character.toString(stack.pop()) + Character.toString(c))) {
        				return false;
        			}
        		} catch (StackUnderflowException e) {
        			return false;
        		}
        	}
        }
    	// if stack is empty, then all the brackets were matched and string is well balanced
    	if (stack.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
