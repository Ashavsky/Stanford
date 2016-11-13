package quiz;

public class QResponseAnswer implements Answer{
	// The answer for this question type is a String 
	
	private String answerStr; 
	private int answerId; 
	
	public QResponseAnswer(String answerStr, int answerId) {
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
