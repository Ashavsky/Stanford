//package quiz;
//import java.util.*;
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class QuizTest {
//
//	private Quiz quiz;
//	@Before
//	public void initQuiz() {
//		List<Question> questions = new ArrayList<Question>();
//    	String q1 = "What is 2+2?";
//    	Set<Answer> answers1 = new HashSet<Answer>();
//    	QResponseAnswer qra1 = new QResponseAnswer("4", 0);
//    	answers1.add(qra1);
//    	QResponse qr1 = new QResponse(q1, answers1, 1);
//    	String q2 = "What is 3+3?";
//    	Set<Answer> answers2 = new HashSet<Answer>();
//    	QResponseAnswer qra2 = new QResponseAnswer("6", 1);
//    	answers2.add(qra2);
//    	QResponse qr2 = new QResponse(q2, answers2, 2);
//    	String q3 = "What is 4+4?";
//    	Set<Answer> answers3 = new HashSet<Answer>();
//    	QResponseAnswer qra3 = new QResponseAnswer("8", 2);
//    	answers3.add(qra3);
//    	QResponse qr3 = new QResponse(q3, answers3, 3);
//    	questions.add(qr1);
//    	questions.add(qr2);
//    	questions.add(qr3);
//    	quiz = new Quiz(questions, 0);
//	}
//
//	@Test
//	public void testRandomizeQuestions() {
//		// Tests to see if randomize works properly (Expected behavior, questions should be in a "random order")
//		System.out.println("Before randomize");
//		quiz.questionsToString();
//		System.out.println("After randomize");
//		quiz.randomizeQuestions();
//		quiz.questionsToString();
//	}
//
//}
