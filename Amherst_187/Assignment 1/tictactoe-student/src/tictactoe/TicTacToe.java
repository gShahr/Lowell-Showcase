package tictactoe;

/**
 * An implementation of the TicTacToeGame interface.
 * 
 * ATTENTION STUDENTS: You MUST use exactly this class specification. Do not 
 * rename the class, and do not remove the "implements TicTacToeGame", or
 * you will receive no credit for your submission.
 */
public class TicTacToe implements TicTacToeGame {
	// n represents the number of rows/columns; inputed by the user
	int n;
	// the amount of spaces on the board or n * n
	// Because the board is a square, it allows are methods to be much simpler than if it were a rectangular board
	int boardLength;
	/** Puts the board into a 2d array, making it easy to collect data values from it
	ideal data structure in this scenario because we have a board
	*/
	String[][] tracker;
	// keeps track so that we know whose turn it is
	int counter;
	/**
	 * Constructs a new instance, implementing the TicTacToeGame interface.
	 * 
	 * ATTENTION STUDENTS: You MUST use exactly this signature for your constructor.
	 * Do not rename the class and do not change the argument, or you will receive
	 * no credit for your submission.
	 * 
	 * @param n the length and width of the board; n >= 3
	 */
	// constructor for our class
	/** side note: all the methods are public because they are used by other classes
	    None of them are static because we want to make all these methods part of a every object (game) we create
	    - If they were static, then they wouldn't be copied into each object (game) that we create
	    - This would result in the methods resulting in the same info. each time we created a game because static methods would be shared with all objects of this class
	    - Instead we want them to be instance so that they are part of an object and change depending on what values we put in
	*/
	public TicTacToe(int n) {
		this.n = n;
		counter = 0;
		boardLength = n * n;
		tracker = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tracker[i][j] = " ";
			}
		}
	}
	
	@Override
	// returns n or the number of rows/columns; accessor method
	public int getN() {
		return n;
	}

	@Override
	// provides a string representation of the board printed out to the user every time they make a move
	public String toString() {
		String representation = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				representation += tracker[i][j];
			}
			representation += "\n";
		}
		return representation;
	}

	@Override
	public String getWinner() {
		String winnerRow = "";
		String winnerCol = "";
		// left diagonal
		String winnerLeft = "";
		// right diagonal
		String winnerRight = "";
		String o = "";
		String x = "";
		for (int i = 0; i < n; i++) {
			o += "O";
			x += "X";
		}
		// travering through the first part of the 2d array
		for (int i = 0; i < n; i++) {
			// traversing diagonal values only requires one for loop
			winnerLeft += tracker[i][i];
			winnerRight += tracker[n-1-i][i];
			// traversing through the second part of the 2d array
			for (int j = 0; j < n; j++) {
				winnerRow += tracker[i][j];
				winnerCol += tracker[j][i];
			}
			if (winnerRow.equals(o) || winnerCol.equals(o) || winnerLeft.equals(o) || winnerRight.equals(o)) {
				return "O";
			} else if (winnerRow.equals(x) || winnerCol.equals(x) || winnerLeft.equals(x) || winnerRight.equals(x)) {
				return "X";
			}
			// Have to reset these values after the inside for loop executes because we want each row/col each time we traverse through 1 part of the 2d array, not all of them 
			winnerRow = "";
			winnerCol = "";
		}
		return "";
	}

	@Override
	public String getCurrentPlayer() {
		/** Precondition; if game is over, there is no current player
		    We check this by seeing if there is a winner or there is a draw
		*/
		if (getWinner().equals("X") || getWinner().equals("O") || counter == boardLength) {
			return "";
			// checking whose turn it is by seeing if counter is negative or even
		} else if (counter % 2 == 0) {
			// X Goes first; even 
			return "X";
		} else {
			// O goes second; odd
			return "O";
		}
	}

	@Override
	public boolean isValidMove(int space) {
		// check if space is negative first; otherwise you can't check other preconditions
		// Similarly, we must check if the space is less than the board length; otherwise upcoming if statements won't run
		if (space < 0 || space >= boardLength) {
			return false;
		}
		// preconditions; not a valid move if one of these is true (if space has an 'x', 'o', or game is over)
		if (tracker[space / n][space % n].equals("X") || tracker[space / n][space % n].equals("O") || getCurrentPlayer().equals("")) {
			return false;
		}
		// returns true as long as all other conditions are met
		return true;
	}

	@Override
	public void move(int space) throws IllegalArgumentException {
		if (!(isValidMove(space))) {
			throw new IllegalArgumentException();
		} else if (getCurrentPlayer().equals("X")) {
			tracker[space / n][space % n] = "X";
		} else if (getCurrentPlayer().equals("O")) {
			tracker[space / n][space % n] = "O";
		}
		/** have to put counter at the end because method has to check for current player with the same value of counter
		    Current Player uses the counter variable to determine whose turn it is
		    therefore if we increment it before, getCurrentPlayer() will give us the wrong player
		*/
		counter++;
	}
}
