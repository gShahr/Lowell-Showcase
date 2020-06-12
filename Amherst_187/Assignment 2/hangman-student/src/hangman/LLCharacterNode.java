package hangman;

public class LLCharacterNode {
	private char letter;
	LLCharacterNode next;
	// constructor
	public LLCharacterNode(char letter) {
		this.letter = letter;
		next = null;
	}
	// accessor method to get the letter
	public char getInfo() {
		return letter;
	}
	// mutator method to change the info of the node
	// useful for when we don't want to alter to nodes, just the information
	public void setInfo(char letter) {
		this.letter = letter;
	}
	// accessor method to get the link
	public LLCharacterNode getLink() {
		return next;
	}
	// mutator method to set the link
	public void setLink(LLCharacterNode next) {
		this.next = next;
	}
}
