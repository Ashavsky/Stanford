package quiz;

public class QuizPage {
	private Quiz quiz;  
	private boolean isRandom; 
	private boolean isPractice; 
	private boolean isMulti; 
	private boolean isImmediateCorrection;
	
	public QuizPage(Quiz quiz) {
		this.quiz = quiz; 
		this.isRandom = false; 
		this.isPractice = false;
		this.isMulti = false;
		this.isImmediateCorrection = false; 
	}
	
	// Randomizes the questions upon user request
	public void setRandom() {
		isRandom = true;
		quiz.randomizeQuestions();
	}
	
	public void setPractice() {
		isPractice = true; 
		// TODO: Make it so quiz stats for this practice quiz aren't tracked
	}
	
	public void setMulti() {
		isMulti = true; 
		// TODO: Add code to make quiz appear on multiple pages 
	}
	
	public void setImmediateCorrection() {
		isImmediateCorrection = true; 
		// TODO: Add code for immediate correction 
	}
}
