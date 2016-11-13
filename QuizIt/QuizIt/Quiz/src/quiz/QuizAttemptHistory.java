package quiz;

import java.util.Date;

public class QuizAttemptHistory {
	private int attemptId; 
	private int quizId; 
	private int userId; 
	private int attemptScore; 
	private int attemptPossible; // Total possible score for the quiz
	private long elapsedTime; // Time between start quiz and submit quiz
	private long attemptTime; 
	
	public QuizAttemptHistory(int attemptId, int quizId, int userId, int attemptPossible) {
		this.attemptId = attemptId;
		this.quizId = quizId; 
		this.userId = userId; 
		this.attemptScore = 0; 
		this.attemptPossible = attemptPossible; 
	}
	
	// Sets the "start time" of the quiz attempt 
	public void startAttempt() {
		attemptTime = System.currentTimeMillis(); 
	}
	
	// Sets the total elapsed time for the quiz attempt 
	public void endAttempt() {
		long endTime = System.currentTimeMillis();
		elapsedTime = endTime-attemptTime;
	}
	
	public int getQuizId() {
		return quizId; 
	}
	
	public int getAttemptId() {	
		return attemptId; 
	}
	
	public int getUserId() {
		return userId; 
	}
	
	// After a user has taken a quiz, set the attempt score field
	public void setAttemptScore(int attemptScore) {
		this.attemptScore = attemptScore;
	}
	
	public long getElapsedTime() {
		return elapsedTime; 
	}
	
	public int getAttemptScore() {
		return attemptScore; 
	}
}
