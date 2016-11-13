package quiz;

public class MultipleChoiceAnswer implements Answer{
	// TODO: Decide how the answers should be represented (Strings like "A","B", "C"?)
	// Current implementation assumes possible user responses are strings which
	// represent the multiple choice buttons
	
	private String answerStr; 
	private int answerId; 
	public MultipleChoiceAnswer(String answerStr, int answerId) {
		this.answerStr = answerStr; 
		this.answerId = answerId; 
	}
	
	public String toString() {
		return answerStr; 
	}
	
	public int getId() {
		return answerId; 
	}
	
}
