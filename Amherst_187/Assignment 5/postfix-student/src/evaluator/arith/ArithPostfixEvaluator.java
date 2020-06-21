package evaluator.arith;

import language.Operand;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
		this.stack = new LinkedStack<Operand<Integer>>(); //TODO initialize your LinkedStack
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		// TODO use all of the things you've built so far to 
		// implement the algorithm for postfix expression evaluation
		
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				//TODO What do we do when we see an operand?
				// -> push to stack 
				stack.push(token.getOperand());
				break;
			case OPERATOR: 
				//TODO What do we do when we see an operator?
				// -> pop 2 times or once and compute according to type of operator and then push the result back to the stack
				Operand<Integer> op1 = stack.pop();
				// if operator is negate, then it is unary and has to use only one number
				if (token.getOperator().toString() != "!") {
					Operand<Integer> op0 = stack.pop();
					token.getOperator().setOperand(0, op0);
				}
				token.getOperator().setOperand(1,  op1);
				stack.push(token.getOperator().performOperation());
				break;
			default: 
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}			
		}
		
		//TODO What do we return?
		// -> the result after all the operations -> which is the last value in the stack
		// if the stack size is not equal to 1, it means the postfix notation was invaid
		if (stack.size() != 1) {
			throw new IllegalPostfixExpressionException();
		} else {
			return stack.pop().getValue(); // have to use.getValue() since popping gets us an operand element that stores an integer value
		}
	}

}
