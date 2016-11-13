package quiz;
import java.util.*;
public class MultipleChoice extends Question
{
	
	// Multiple choice question, choices should be fairly important 
	// TODO: Think about how choices should be stored: 
	// TODO: Decide if choices should be included in Question, Answer, or not at all 
	private List<Answer> answerChoices;
	private Set<Answer> correctAnswers; 
	private int questionId;
	private boolean multi;
	
	public MultipleChoice(String question, Set<Answer> correctAnswers, List<Answer> answerChoices, boolean multi, int questionId) {
		super(1, "multiplechoice", question);
		this.correctAnswers = correctAnswers; 
		this.answerChoices = answerChoices; 
		this.questionId = questionId; 
		this.multi = multi;
	}

	// TODO probably remove this
	public boolean checkAnswer(Answer userResponse) {
		// Check if userResponse is in choices  
		String urStr = userResponse.toString(); 
		return correctAnswers.contains(urStr);
	}
	
	//Temporary implementation
	public boolean checkAnswer(String userResponse) {
		return correctAnswers.contains(userResponse);
	}
	
	//Override of toString
	public String toString() {
		return super.getQuestionText();
	}
	
	public int getId() {
		return questionId; 
	}
	
	public List<Answer> getAnswerChoices() {
		return answerChoices; 
	}
	
	public String getQuestionType() {
		return super.getQuestionType();
	}
	
}
