package quiz;

import java.util.Date;

public class QuizSummary {
	private int quizId;
	private String quizName; 
	private String quizDescription; 
	private int creatorId;
	private String createDate;
	
	public QuizSummary(String quizName, String quizDescription, int creatorId, String createDate)
	{
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.creatorId = creatorId;
		this.createDate = createDate;
	}

	public QuizSummary(String quizName, String quizDescription, int creatorId, String createDate, int quizId)
	{
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.creatorId = creatorId;
		this.createDate = createDate;
		this.quizId = quizId;
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

	public String getCreateDate() {
		return createDate; 
	}

	public void setQuizId(int value) {this.quizId = value; }
}
