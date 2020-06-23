package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {
	
	StackBasedHanoiPeg[] board;
	int size; // only size of the 3rd peg matters because it determines if the user has won
	int winSize; // the needed number of rings on the 3rd peg in order to win the game
	StackBasedHanoiPeg peg1, peg2, peg3;
	
	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	
	public ArrayBasedHanoiBoard(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else {
			board = new StackBasedHanoiPeg[3];
			peg1 = new StackBasedHanoiPeg();
			peg2 = new StackBasedHanoiPeg();
			peg3 = new StackBasedHanoiPeg();
			board[0] = peg1;
			board[1] = peg2;
			board[2] = peg3;
			for (int i = n; i > 0; i--) {
				board[0].addRing(new HanoiRing(i));
			}
			size = 0;
			winSize = n;
		}
	}

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		if (!isLegalMove(move)) {
			throw new IllegalHanoiMoveException(
					"Could not perform illegal move.");
		}
		board[move.getToPeg()].addRing(board[move.getFromPeg()].remove());
		if (move.getToPeg() == 2) {
			size++;
		} 
		if (move.getFromPeg() == 2) {
			size--;
		}
	}

	@Override
	public boolean isSolved() {
		if (size == winSize) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLegalMove(HanoiMove move) {
		if (move == null) {
			throw new NullPointerException();
		} else if (!board[move.getToPeg()].hasRings()) {
			return true;
		} else if (size == winSize || !board[move.getFromPeg()].hasRings()) {
			return false;
		} else {
			return board[move.getFromPeg()].getTopRing().getSize() < board[move.getToPeg()].getTopRing().getSize();
		}
	}
}
