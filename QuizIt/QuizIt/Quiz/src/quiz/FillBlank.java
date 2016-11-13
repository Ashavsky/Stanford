package quiz;
import java.util.*; 
public class FillBlank implements Question{
	/* Pretty much identical to QResponse
	 * However, the question string will represent the blank as the '|' character
	 * i.e. "The | is the largest animal ever known to have lived."
	 */
	
	private String question; 
	private Set<Answer> possibleAnswers; 
	private int questionId; 
	private String questionType; 
	public FillBlank(String question, Set<Answer> possibleAnswers, int questionId) {
		this.questionType = "fillblank";
		this.question = question; 
		this.possibleAnswers = possibleAnswers;
		this.questionId = questionId; 
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
