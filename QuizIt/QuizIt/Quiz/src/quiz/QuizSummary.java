package quiz;

import java.util.Date;

public class QuizSummary {
	private int quizId;
	private String quizName; 
	private String quizDescription; 
	private int creatorId; 
	//TODO: Decide how to represent createDate 
	private Date createDate; 
	
	public QuizSummary(int quizId, String quizName, String quizDescription, int creatorId) {
		this.quizId = quizId; 
		this.quizName = quizName; 
		this.quizDescription = quizDescription; 
		this.creatorId = creatorId;
		Date now = new Date(); 
		this.createDate = now;  
	}
	
	public int getQuizId() {
		return quizId; 
	}
	
	public String getQuizName() {
		return quizName; 
	}
	
	public String getQuizDescription() {
		return quizDescription; 
	}
	
	public int getCreatorId() {
		return creatorId; 
	}
	
	// TODO: Make sure this is how we want to represent date/time of creation
	public Date getCreateDate() {
		return createDate; 
	}
}
