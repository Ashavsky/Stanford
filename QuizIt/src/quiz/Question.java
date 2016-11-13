package quiz;

public interface Question {
	//Override of toString(): Returns the question in a String format 
	public String toString(); 
	
	// Returns the correct answer to the question  
	// TODO: Decide if getAnswer() is needed or not 
	//public Answer getAnswer();
	
	// Checks if the user's response to the question is the correct answer 
	public boolean checkAnswer(Answer answer);
	
}
