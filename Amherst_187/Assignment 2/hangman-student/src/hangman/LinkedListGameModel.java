package hangman;

public class LinkedListGameModel implements GameModel {
	// variables specific to the hangman game
	private int state;
	private String guessWord;
	int uniqueGuessCounter;
	// keeping track of where we are in the list; linkedList data for guesses and the board
	LLCharacterNode headGame;
	LLCharacterNode headGuesses;

	public LinkedListGameModel(String guessWord) {
		// same as array for these 3 variables
		state = STARTING_STATE;
		this.guessWord = guessWord;
		uniqueGuessCounter = 0;
		// set this to null because it is empty
		headGuesses = null;
		// initialize the gameBoard with '_' for all nodes
		// head node will start with '_' because no guesses have been made
		headGame = new LLCharacterNode('_');
		// create a current value because we don't want to lose track of where the 1st node in the linked list is
		LLCharacterNode current = headGame;
		// iterate the length of the guessWord to fill the linked list with '_' characters
		for (int i = 0; i < guessWord.length() - 1; i++) {
			// creates nodes pointing to '_' character
			// create a new node each time to make a linked list
			LLCharacterNode node = new LLCharacterNode('_');
			current.setLink(node);
			current = current.getLink();
		}
	}
	
	@Override  
	public boolean isPriorGuess(char guess) {
		// create a current node so that we can traverse through the linked list and keep track of where the 1st node is
		LLCharacterNode current = headGuesses;
		// keep iterating through until we are at the last node in the list; aka when we reach a null value
		while (current != null) {	
			if (current.getInfo() == guess) {
				return true;
			}
			current = current.getLink();
		}
		return false;
	}

	@Override
	// returns number of unique guesses user has made
	public int numberOfGuesses() {
		return uniqueGuessCounter;
	}

	@Override
	// same as array implementation because we are just using the previous method and comaring the guess with the string, not the linked list
	public boolean isCorrectGuess(char guess) {
		// checks to see if the character is in the word
		return guessWord.indexOf(guess) != -1;

	}

	@Override
	public boolean doMove(char guess) {
		// no point to traverse through the rest of the method if guess was a prior guess
		if (isPriorGuess(guess)) {
			return false;
		}
		// adds guess to guessList and increases amount of unique guesses the user has made
		// always changes regardless if user made an incorrect or correct guess
		addGuesses(guess);
		uniqueGuessCounter++;
		// correct guesses
		if (isCorrectGuess(guess)) {
			LLCharacterNode current = headGame;
			for (char ch: guessWord.toCharArray()) {
				if (ch == guess) {
					// set the value rather than trying to put another node in because that would require me to add a node and then remove one; 
					// much easier to just change the value of the node
					current.setInfo(guess);
				}
				current = current.getLink();
			}
			// update the collection of guesses the user has made; correct guesses
			// we don't increment the state of the game because the user guessed correctly
			return true;
		// incorrect guesses
		} else {
			state++;
			return false;
		}
	}

	
	public void addGuesses(char guess) {
		// have to initialize the head node if it was previously null in order to create our linked list for the user's guesses
		if (headGuesses == null) {
			headGuesses = new LLCharacterNode(guess);
		} else {
			LLCharacterNode node = new LLCharacterNode(guess);
			LLCharacterNode current = headGuesses;
			while (current.getLink() != null) {
				current = current.getLink();
			}
			current.setLink(node);
		}
	}
		
	@Override
	public boolean inWinningState() {
		LLCharacterNode current = headGame;
		while (current != null) {
			if (current.getInfo() == '_') {
				return false;
			}
			current = current.getLink();
		}
		return true;
	}

	@Override
	public boolean inLosingState() {
		return (!(inWinningState())) && state == 10;
	}
	
	// returns a representation of the board
	public String toString() {
		String board = "";
		LLCharacterNode current = headGame;
		while (current != null) {
			board += String.valueOf(current.getInfo()) + ' ';
			current = current.getLink();
		}		
		// return substring of board up until the last space to get rid of the extra space
		return (board.substring(0, board.length() - 1));
	}

	@Override
	// returns the state the user is in
	public int getState() {
		return state;
	}

	@Override
	public String previousGuessString() {
		// set a current value to the head node so that we can traverse through the linked list
		LLCharacterNode current = headGuesses;
		// returns empty square brackets if no guesses have been made
		if (current == null) {
			return "[]";
		// otherwise returns the values the nodes are pointing to
		} else {
			String previousGuesses = "[";
			while (current != null) {
				previousGuesses += String.valueOf(current.getInfo()) + ", ";
				current = current.getLink();
			}
			// returns a substring so that it removes the extra comma and space at the end
			return previousGuesses.substring(0, previousGuesses.length()-2) + "]";
		}
	}

	@Override
	// returns the word the user is trying to guess
	public String getWord() {
		return guessWord;
	}
}
