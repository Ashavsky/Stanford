package quiz;

import java.util.*;

public class QResponseAnswer extends Answer
{
	// The answer for this question type is a String 
	
	private Set<String> answerAlts;
	private boolean available;
	
	public QResponseAnswer(Set<String> answerAlternatives, int answerId)
	{
		super(answerId, 1, "pictureresponse", "answerplaceholder", false);
		this.answerAlts = answerAlternatives;
		this.available = true;
	}

	public String toString() {
		return String.join(" OR ", answerAlts); 
	}

	public boolean matches(String ans) {
		if (available && answerAlts.contains(ans)) {
			available = false;
			return true;
		}
		return false;
	}

	public int getId() {
		return super.getAnswerId();
	}
}
