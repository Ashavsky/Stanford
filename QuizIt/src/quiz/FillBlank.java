package quiz;

public class FillBlank implements Question{
	/* Pretty much identical to QResponse
	 *
	 * TODO: One design choice we can make is how to represent the question string. 
	 * Could have it as two separate strings and separate on the blank or as one string. 
	*/
	private String firstHalfQ;
	private String secondHalfQ; 
	private Answer answer; 
	
	public FillBlank(String firstHalfQ, String secondHalfQ, Answer answer) {
		this.firstHalfQ = firstHalfQ;
		this.secondHalfQ = secondHalfQ; 
		this.answer = answer;
	}
	
	public Answer getAnswer() {
		return answer;
	}  
	
	public boolean checkAnswer(Answer userResponse) {
		String lowercaseResponse = userResponse.toString().toLowerCase();
		String lowercaseAnswer = answer.toString().toLowerCase();
		if (lowercaseAnswer.equals(lowercaseResponse)) {
			return true; 
		}
		return false; 
	}
	
	public String toString() {
		return firstHalfQ + "   " + secondHalfQ; 
	}
}
