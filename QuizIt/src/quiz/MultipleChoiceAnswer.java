package quiz;

public class MultipleChoiceAnswer implements Answer{
	// TODO: Decide how the answers should be represented (Strings like "A","B", "C"?)
	// Current implementation assumes possible user responses are strings which
	// represent the multiple choice buttons
	
	private String answerStr; 
	
	public MultipleChoiceAnswer(String answerStr) {
		this.answerStr = answerStr; 
	}
	
	public String toString() {
		return answerStr; 
	}
	
}
