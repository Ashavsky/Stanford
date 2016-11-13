package quiz;

public class QResponseAnswer implements Answer{
	// The answer for this question type is a String 
	
	private String answerStr; 
	public QResponseAnswer(String answerStr) {
		this.answerStr = answerStr; 
	}
	
	public String toString() {
		return answerStr; 
	}
}
