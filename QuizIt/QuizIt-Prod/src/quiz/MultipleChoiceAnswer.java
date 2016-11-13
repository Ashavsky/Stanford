package quiz;

public class MultipleChoiceAnswer extends Answer
{
	public MultipleChoiceAnswer(String answerStr, int answerId)
	{
		super(answerId, 1, "multi", answerStr, false);
	}
	
	public String toString() {
		return super.getAnswerText();
	}
	
	public int getId() {
		return super.getAnswerId();
	}
	
}
