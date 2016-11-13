package quiz;
import java.util.*; 

public class QResponse implements Question {
	/*
	 * This is a standard text question with an appropriate text response. For
	 * example: Who was President during the Bay of Pigs fiasco?
	 */

	private String question;
	//private Answer answer;
	private int questionId; 
	private Set<Answer> possibleAnswers; 
	private String questionType; 
	
	public QResponse(String question, Set<Answer> possibleAnswers, int questionId) {
		this.questionType = "qresponse";
		this.question = question;
		this.questionId = questionId; 
		this.possibleAnswers = possibleAnswers; 
	}
	
	// Current implementation: accepts any capitalization i.e. washington or Washington
	public boolean checkAnswer(Answer userResponse) {
		String lowercaseResponse = userResponse.toString().toLowerCase();
		for(Answer currAnswer: possibleAnswers) {
			if(currAnswer.toString().toLowerCase().equals(lowercaseResponse)) {
				return true; 
			}
		}
		return false; 
	}
	
	// Checks answer given answer string as a parameter 
	public boolean checkAnswer(String userResponse) {
		String lowercaseResponse = userResponse.toLowerCase();
		for(Answer currAnswer: possibleAnswers) {
			if(currAnswer.toString().toLowerCase().equals(lowercaseResponse)) {
				return true; 
			}
		}
		return false; 
	}
	
	public String toString() {
		return question; 
	}
	
	public int getId() {
		return questionId; 
	}
	
	public String getQuestionType() {
		return questionType; 
	}
	
}
