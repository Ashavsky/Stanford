package quiz;

import java.util.List;

public class Question
{
	private int questionId = 0;
	private int quizId;
	private String questionType;
	private String questionText;
	private List<Answer> answers;

	public Question(int quizzId, String questionType, String questionText)
	{
		this.quizId = quizzId;
		this.questionType = questionType;
		this.questionText = questionText;
	}
	public Question(int questionId,int quizId, String questionType, String questionText)
	{
		this.questionId = questionId;
		this.quizId = quizId;
		this.questionType = questionType;
		this.questionText = questionText;
	}

	public int getQuestionId() {return questionId;}
	public int getQuizId() {return quizId;}
	public void setQuizId(int id) { this.quizId = id; }
	public String getQuestionType() {return questionType;}
	public String getQuestionText() {return questionText;}
	public void setQuestionId(int id) {this.questionId = id;}
	public List<Answer> getAnswers() { return answers; }
	public void setAnswers(List<Answer> answers) { this.answers = answers; }

	public boolean checkAnswer(String currAnswer)
	{
		return false;
	}
}
