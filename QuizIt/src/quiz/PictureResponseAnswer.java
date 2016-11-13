package quiz;

public class PictureResponseAnswer implements Answer{
	private String answer; 
	
	public PictureResponseAnswer(String answer) {
		this.answer = answer; 
	}
	
	public String toString(){
		return answer; 
	}
}
