package quiz;

public class PictureResponse implements Question{
	private String question; 
	private String imageURL; 
	private Answer answer; 
	private int questionId; 
	private String questionType; 
	
	public PictureResponse(String question, String imageURL, Answer answer, int questionId) {
		this.questionType = "pictureresponse";
		this.question = question; 
		this.imageURL = imageURL; 
		this.answer = answer; 
		this.questionId = questionId; 
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
	
	public boolean checkAnswer(String userResponse) {
		String lowercaseResponse = userResponse.toLowerCase();
		String lowercaseAnswer = answer.toString().toLowerCase();
		if (lowercaseAnswer.equals(lowercaseResponse)) {
			return true; 
		}
		return false; 
	}
	
	public String toString() {
		return imageURL; 
	}
	
	public int getId() {
		return questionId; 
	}
	
	public String getQuestionType() {
		return questionType; 
	}
}
