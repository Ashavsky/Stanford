package quiz;

public class PictureResponse implements Question{
	private String imageURL; 
	private Answer answer; 
	
	public PictureResponse(String imageURL, Answer answer) {
		this.imageURL = imageURL; 
		this.answer = answer; 
	}
	
	public Answer getAnswer() {
		return answer; 
	}
	
	// Currently accepts non-case sensitive response to the image 
	public boolean checkAnswer(Answer userResponse) {
		String lowercaseResponse = userResponse.toString().toLowerCase();
		String lowercaseAnswer = answer.toString().toLowerCase();
		if (lowercaseAnswer.equals(lowercaseResponse)) {
			return true; 
		}
		return false; 
	}
	
	public String toString() {
		return imageURL; 
	}
}
