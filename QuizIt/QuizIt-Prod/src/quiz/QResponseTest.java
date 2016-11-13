package quiz;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class QResponseTest {

	@Test
	public void testQResponse() {
		// Basic test to see if question response format is working 
		String q1 = "What is 2+2?";
		List<Answer> possibleAnswers = new ArrayList<Answer>();
		QResponseAnswer qra1 = new QResponseAnswer(new HashSet<String>(Arrays.asList("4")), 0);
		possibleAnswers.add(qra1);
		QResponse qr1 = new QResponse(q1, possibleAnswers, 1, false, 0);
		assertTrue(qr1.checkAnswer("4"));
		assertTrue(!qr1.checkAnswer("2"));
	}
	
	@Test
	public void testQResponse2() {
		// Test to see if QResponse accepts answers with different capitalizations 
		String q1 = "Who was the US's first President?"; 
		List<Answer> possibleAnswers = new ArrayList<Answer>(); 
		QResponseAnswer qra1 = new QResponseAnswer(
				new HashSet<String>(Arrays.asList("George Washington", "gw")), 0);
		QResponseAnswer qra2 = new QResponseAnswer(new HashSet<String>(Arrays.asList("washington")), 1);
		possibleAnswers.add(qra1);
		possibleAnswers.add(qra2);
		QResponseAnswer qra5 = new QResponseAnswer(new HashSet<String>(Arrays.asList("George")), 4); // Don't accept this one 
		QResponse qr1 = new QResponse(q1, possibleAnswers, 1, false, 0);
		assertTrue(qr1.checkAnswer("GW"));
		assertTrue(!qr1.checkAnswer("George Washington")); 
		assertTrue(qr1.checkAnswer("Washington"));
		assertTrue(!qr1.checkAnswer("George"));
	}
}
