package quiz;

public class FillBlankAnswer implements Answer{
	// The answer for this question type is a String 
	
	private String answerStr; 
	public FillBlankAnswer(String answerStr) {
		this.answerStr = answerStr; 
	}
	
	public String toString() {
		return answerStr; 
	}
}
