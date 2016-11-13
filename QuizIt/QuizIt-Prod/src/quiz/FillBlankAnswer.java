package quiz;

public class FillBlankAnswer extends Answer
{
	public FillBlankAnswer(String answerStr, int answerId)
	{
		super(answerId, 1, "fillblank", answerStr, false);
	}
	
	public String toString() {
		return super.getAnswerText();
	}
	
	public int getId() {
		return super.getAnswerId();
	}
}
