package quiz;

import java.util.Date;

public class QuizAttempt
{
	private int attemptId; 
	private int quizId; 
	private int userId; 
	private int attemptScore; 
	private int attemptPossible; // Total possible score for the quiz
	private long elapsedTime; // Time between start quiz and submit quiz
	private String dateCreated;
	
	public QuizAttempt(int quizId, int userId, int attemptScore, int attemptPossible, long elapsedTime, String dateCreated) {
		this.quizId = quizId; 
		this.userId = userId; 
		this.attemptScore = attemptScore;
		this.attemptPossible = attemptPossible;
		this.elapsedTime = elapsedTime;
		this.dateCreated = dateCreated;
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

	public int getAttemptPossible() {
		return attemptPossible;
	}

	public void setAttemptId(int value) {this.attemptId = value;}

	public String getDateCreated() {return dateCreated;}
}
