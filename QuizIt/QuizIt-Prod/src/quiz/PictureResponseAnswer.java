package quiz;

public class PictureResponseAnswer extends Answer
{
	public PictureResponseAnswer(String answer, int answerId)
	{
		super(answerId, 1, "pictureresponse", answer, false);

	}
	
	public String toString(){
		return super.getAnswerText();
	}
	
	public int getId() {return super.getAnswerId();}
}
