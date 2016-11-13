package quiz;

public class PictureResponse extends Question{
	private String imageURL; 
	private Answer answer; 
	private int questionId;
	
	public PictureResponse(String question, String imageURL, Answer answer, int questionId) {
		super(1, "pictureresponse", question);
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
		return super.getQuestionText();
	}

	public String getImageURL() { return imageURL; }

	public int getId() {
		return questionId; 
	}
	
	public String getQuestionType() {
		return super.getQuestionType();
	}
}
