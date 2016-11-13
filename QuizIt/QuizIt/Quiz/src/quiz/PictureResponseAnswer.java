package quiz;

public class PictureResponseAnswer implements Answer{
	private String answer; 
	private int answerId; 
	
	public PictureResponseAnswer(String answer, int answerId) {
		this.answer = answer; 
		this.answerId = answerId; 
	}
	
	public String toString(){
		return answer; 
	}
	
	public int getId() {
		return answerId; 
	}
}
