package hangman;

import java.util.Arrays;

/**
 * The Array implementation of the GameModel interface.
 */
public class ArrayGameModel implements GameModel {
	/** The number of characters (lower/upper). */
	private static final int ALPHABET_COUNT = 26*2;
	
	/** hung state */
	private int state;
	// keeping track of what the guessWord is so that I can use it in other methods
	private String guessWord;
	// keeps track of the number of guesses
	int uniqueGuessCounter;
	// keeps track of all the guesses the user made
	char[] guesses;
	// keeps track of correctly guessed letters by the user
	char[] gameBoard;
	
	/**
	 * Creates a new ArrayGameModel object.
	 * 
	 *  guessWord the word to guess
	 */
	public ArrayGameModel(String guessWord) {
		state = STARTING_STATE;
		this.guessWord = guessWord;
		uniqueGuessCounter = 0;
		guesses = new char[52];
		// represents the game board or what the secret word the user has to guess in order to win
		gameBoard = new char[guessWord.length()];
		// tracks where the letter should go for the array
		for (int i = 0; i < guessWord.length(); i++) {
			gameBoard[i] = '_';
		}
		// initialize the array so we don't have to deal with default values when printing it out as a string
		for (int i = 0; i < guesses.length; i++) {
			guesses[i] = '_';
		}
	}
		
	public boolean isPriorGuess(char guess) {
		// returns true if the character is not in the unique string of guesses that have already been guessed by the user
		for (char ch: guesses) {
			if (ch == guess) {
				return true;
			}
		}
		return false;
	}
	
	public int numberOfGuesses() {
		// returns number of unique guesses the user has made; excludes repeated guesses
		return uniqueGuessCounter;
	}
	
	public boolean isCorrectGuess(char guess) {
		// returns true if it is NOT a prior guess and that the guessword contains the character the user guessed
		return !(isPriorGuess(guess)) &&  guessWord.indexOf(guess) != -1;
	}
	
	public boolean doMove(char guess) {
		// correct guesses
		if (isCorrectGuess(guess)) {
			/** takes advantage of array's random access feature 
		    Since gameBoard is a direct representation of the string, 
		    we are able to access where the guess needs to go based on what index 
		    of the the guess is for the guessWord  */
			// The above thought process fails to fill in the gameboard for repeating letters
			int counter = 0;
			// iterate through the guessWord and if the guess matches, add the letter to each spot where it is in the guessWord
			// This makes sure to account for REPEATING letters in the guessWord
			for (char ch: guessWord.toCharArray()) {
				if (ch == guess) {
					gameBoard[counter] = guess;
				}
				counter++;
			}
			// update the collection of guesses the user has made; correct guesses
			guesses[uniqueGuessCounter] = guess;
			// we don't increment the state of the game because the user guessed correctly
			uniqueGuessCounter++;
			return true;
			// repeated guesses
			// If a guess was a PREVIOUS guess, then the state/uniqueCounter do not increase and the method just returns false
			// we don't care if the guess was correct or not; just if we guessed it previously
		}  else if (isPriorGuess(guess)) {
			return false;
		}
		// incorrect guesses
		// increments the state and guessCounter if the user guessed incorrectly
		// state only goes up if the user guessed incorrectly
		// adds the incorrect guesses to the array; incorrect guesses
		guesses[uniqueGuessCounter] = guess;
		// must put counter after adding guess to guesses; otherwise will increment incorrectly
		uniqueGuessCounter++;
		state++;
		return false;
	}
	
	// if gameBoard is filled out completely and correctly, return true; false otherwise
	public boolean inWinningState() {
		for (char ch: gameBoard) {
			if (ch == '_') {
				return false;
			}
		}
		return true;
	}

	public boolean inLosingState() {
		// returns true if the game is in the final state and is not in a winningState
		// in other words, if the user lost the game
		return (!(inWinningState())) && state == 10;
	}
	
	// returns a string representation of the board
	public String toString() {
		String board = "";
		for (char ch: gameBoard) {
			board += String.valueOf(ch) + " ";
		}		
		return board.substring(0, board.length() - 1);
	}

	public String previousGuessString() {
		String previousGuesses = "[";
		for (int i = 0; i < guesses.length; i++) {
			if (guesses[i] == '_') {
				break;
			}
			previousGuesses += String.valueOf(guesses[i]) + ", ";
		}
		// returns empty square brackets if no guesses have been made
		if (previousGuesses.length() == 0) {
			return "[]";
		}
		// returns a substring so that it removes the extra comma and space at the end
		return previousGuesses.substring(0, previousGuesses.length()-2) + "]";
	}
	
	// accessor method to get the state of the game
	public int getState() {
		return state;
	}

	// accessor method to get the word the user is trying to guess
	public String getWord() {
		return guessWord;
	}
  
}
