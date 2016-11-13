package quiz;
import java.util.*; 
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuizTest {
	
	private Quiz quiz; 
	@Before 
	public void initQuiz() {
		List<Question> questions = new ArrayList<Question>();
		String q1 = "What is 2+2?"; 
		QResponseAnswer qra1 = new QResponseAnswer("4");
		QResponse qr1 = new QResponse(q1, qra1);
		String q2 = "What is 3+3?"; 
		QResponseAnswer qra2 = new QResponseAnswer("6");
		QResponse qr2 = new QResponse(q2, qra2);
		String q3 = "What is 4+4?"; 
		QResponseAnswer qra3 = new QResponseAnswer("8");
		QResponse qr3 = new QResponse(q3, qra3);
		questions.add(qr1);
		questions.add(qr2);
		questions.add(qr3);
		quiz = new Quiz(questions);
	}
	
	@Test
	public void testRandomizeQuestions() {
		// Tests to see if randomize works properly (Expected behavior, questions should be in a "random order") 
		System.out.println("Before randomize");
		quiz.questionsToString(); 
		System.out.println("After randomize");
		quiz.randomizeQuestions();
		quiz.questionsToString();
	}

}
