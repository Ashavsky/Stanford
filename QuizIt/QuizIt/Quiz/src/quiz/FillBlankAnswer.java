package quiz;

public class FillBlankAnswer implements Answer{
	// The answer for this question type is a String 
	
	private String answerStr; 
	private int answerId; 
	
	public FillBlankAnswer(String answerStr, int answerId) {
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
