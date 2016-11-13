package quiz;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class QResponseTest {

	@Test
	public void testQResponse() {
		// Basic test to see if question response format is working 
		String q1 = "What is 2+2?"; 
		Set<Answer> possibleAnswers = new HashSet<Answer>(); 
		QResponseAnswer qra1 = new QResponseAnswer("4", 0);
		QResponseAnswer qra2 = new QResponseAnswer("2", 1);
		possibleAnswers.add(qra1);
		QResponse qr1 = new QResponse(q1, possibleAnswers, 0);
		assertTrue(qr1.checkAnswer("4"));
		assertTrue(!qr1.checkAnswer("2")); 
	}
	
	@Test
	public void testQResponse2() {
		// Test to see if QResponse accepts answers with different capitalizations 
		String q1 = "Who was the US's first President?"; 
		Set<Answer> possibleAnswers = new HashSet<Answer>(); 
		QResponseAnswer qra1 = new QResponseAnswer("George Washington", 0);
		QResponseAnswer qra2 = new QResponseAnswer("gw", 1);
		QResponseAnswer qra3 = new QResponseAnswer("GW", 2);
		QResponseAnswer qra4 = new QResponseAnswer("WASHINGTON", 3);
		possibleAnswers.add(qra1);
		possibleAnswers.add(qra2);
		possibleAnswers.add(qra3);
		possibleAnswers.add(qra4);
		QResponseAnswer qra5 = new QResponseAnswer("George", 4); // Don't accept this one 
		QResponse qr1 = new QResponse(q1, possibleAnswers, 0);
		assertTrue(qr1.checkAnswer(qra1));
		assertTrue(qr1.checkAnswer(qra2)); 
		assertTrue(qr1.checkAnswer(qra3)); 
		assertTrue(qr1.checkAnswer(qra4)); 
		assertTrue(!qr1.checkAnswer(qra5));
	}
}
