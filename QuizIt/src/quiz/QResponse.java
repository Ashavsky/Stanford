package quiz;

public class QResponse implements Question {
	/*
	 * This is a standard text question with an appropriate text response. For
	 * example: Who was President during the Bay of Pigs fiasco?
	 */

	private String question;
	private Answer answer;

	public QResponse(String question, Answer answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Answer getAnswer() {
		return answer;
	}  
	
	
	// Current implementation: accepts any capitalization i.e. washington or Washington
	// TODO: Consider adding a set of possible acceptable answers i.e. George Washington and Washignton 
	public boolean checkAnswer(Answer userResponse) {
		String lowercaseResponse = userResponse.toString().toLowerCase();
		String lowercaseAnswer = answer.toString().toLowerCase();
		if (lowercaseAnswer.equals(lowercaseResponse)) {
			return true; 
		}
		return false; 
	}
	
	public String toString() {
		return question; 
	}
	
}
