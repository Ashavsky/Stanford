package quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer
{
	private int answerId;
	private int questionId;
	private String answerType;
	private String answerText;
	private boolean isAnswerCorrect;

	public Answer(int questionId, String answerType, String answerText, boolean isAnswerCorrect)
	{
		this.questionId = questionId;
		this.answerType = answerType;
		this.answerText = answerText;
		this.isAnswerCorrect = isAnswerCorrect;
	}
	public Answer(int answerId,int questionId, String answerType, String answerText, boolean isAnswerCorrect)
	{
		this.answerId = answerId;
		this.questionId = questionId;
		this.answerType = answerType;
		this.answerText = answerText;
		this.isAnswerCorrect = isAnswerCorrect;
	}

	public int getAnswerId() {return answerId;}
	public int getQuestionId() {return questionId;}
	public String getAnswerType() {return answerType;}
	public String getAnswerText() {return answerText;}
	public boolean getAnswerCorrectFlag() {return isAnswerCorrect;}
	public void setAnswerId(int id) {this.answerId = id;}
	public void setQuestionId(int id) {this.questionId = id;}

	public static List<String> answerToList(String str)
	{
		List<String> answerList = Arrays.asList(str.split("\\s*;\\s*"));;
		return answerList;
	}

}
