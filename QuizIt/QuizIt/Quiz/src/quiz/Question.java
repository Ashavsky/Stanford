package quiz;

public interface Question {
	//Override of toString(): Returns the question in a String format 
	public String toString(); 
	
	// Checks if the user's response to the question is the correct answer 
	public boolean checkAnswer(Answer answer);
	
	// If the expected answer is a string, checks answer (Return null if a string is unexpected!) 
	public boolean checkAnswer(String answer); 
	
	//Returns the question ID
	public int getId();
	
	// Return Question Type 
	/* "qresponse", "fillblank", "pictureresponse", "multiplechoice"
	 * 
	 */
	public String getQuestionType();
	
}
