package quiz;
import java.util.*; 
public class Quiz
{
	public int quizId;
	public QuizSummary summary;
	public QuizStats stats;
	public List<Question> questions;

	public Quiz(int quizId, QuizSummary summary, QuizStats stats, List<Question> questions)
	{
		this.quizId = quizId;
		this.summary = summary;
		this.stats = stats;
		this.questions = questions;
	}
	public Quiz(List<Question> questions, int quizId)
	{
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

	public List<Question> getQuestions() {return questions; }

	public int getId(){return quizId;}}
