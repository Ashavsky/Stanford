package quiz;

import static org.junit.Assert.*;

import org.junit.Test;

public class QResponseTest {

	@Test
	public void testQResponse() {
		// Basic test to see if question response format is working 
		String q1 = "What is 2+2?"; 
		QResponseAnswer qra1 = new QResponseAnswer("4");
		QResponseAnswer qra2 = new QResponseAnswer("2");
		QResponse qr1 = new QResponse(q1, qra1);
		assertTrue(qr1.checkAnswer(qra1));
		assertTrue(!qr1.checkAnswer(qra2)); 
	}
	
	@Test
	public void testQResponse2() {
		// Test to see if QResponse accepts answers with different capitalizations 
		String q1 = "Who was the US's first President?"; 
		QResponseAnswer qra1 = new QResponseAnswer("George Washington");
		QResponseAnswer qra2 = new QResponseAnswer("george Washington");
		QResponseAnswer qra3 = new QResponseAnswer("george washington");
		QResponseAnswer qra4 = new QResponseAnswer("george WASHINGTON");
		QResponseAnswer qra5 = new QResponseAnswer("Washington");
		QResponse qr1 = new QResponse(q1, qra1);
		assertTrue(qr1.checkAnswer(qra1));
		assertTrue(qr1.checkAnswer(qra2)); 
		assertTrue(qr1.checkAnswer(qra3)); 
		assertTrue(qr1.checkAnswer(qra4)); 
		assertTrue(!qr1.checkAnswer(qra5)); 
	}
}
