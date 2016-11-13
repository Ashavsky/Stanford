package quiz;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*; 
public class FillBlankTest {

	@Test
	public void testFillBlank() {
		// Similar quiz to question response, checks if the guaranteed interface methods work 
		String question = "The elephant is the largest land | ."; 
		FillBlankAnswer fba1 = new FillBlankAnswer("mammal", 0);
		FillBlankAnswer fba2 = new FillBlankAnswer("lizard", 1);
		Set<Answer> answers1 = new HashSet<Answer>(); 
		answers1.add(fba1);
		FillBlank fb1 = new FillBlank(question, answers1, 0);
		assertTrue(fb1.checkAnswer(fba1));
		assertTrue(!fb1.checkAnswer(fba2)); 
	}
	
	@Test
	public void testFillBlank2() {
		// Test if we accept different capitalizations 
		String question = "The | is the largest animal ever known to have lived.";
		FillBlankAnswer fba1 = new FillBlankAnswer("blue whale", 0);
		FillBlankAnswer fba2 = new FillBlankAnswer("Blue Whale", 1);
		FillBlankAnswer fba3 = new FillBlankAnswer("BLUE WHALE", 2);
		FillBlankAnswer fba4 = new FillBlankAnswer("blu whale", 3);
		Set<Answer> answers1 = new HashSet<Answer>();
		answers1.add(fba1);
		FillBlank fb1 = new FillBlank(question, answers1, 0);
		assertTrue(fb1.checkAnswer(fba1));
		assertTrue(fb1.checkAnswer(fba2));
		assertTrue(fb1.checkAnswer(fba3));
		assertTrue(!fb1.checkAnswer(fba4));
	}
}
