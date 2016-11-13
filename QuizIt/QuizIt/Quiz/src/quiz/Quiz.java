package quiz;
import java.util.*; 
public class Quiz {
	
	// TODO: Might want to track user statistics in the future for the quiz summary page
	// Example fields for the quiz summary page to store this info in the database. 
	private int quizId; 

	private List<Question> questions;
	
	public Quiz(List<Question> questions, int quizId) {
		this.questions = questions; 
		this.quizId = quizId; 
	}
	
	// Randomizes the list of questions if the player wants random questions 
	public void randomizeQuestions() {
		Collections.shuffle(questions);
	}
	
	public void questionsToString() {
		for(int i = 0; i < questions.size(); i++) {
			System.out.println(questions.get(i).toString());
		}
	}
	
	public List<Question> getQuestions() {
		return questions; 
	}
	
	public int getId(){
		return quizId; 
	}
}
