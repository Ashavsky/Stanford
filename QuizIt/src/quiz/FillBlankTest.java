package quiz;

import static org.junit.Assert.*;

import org.junit.Test;

public class FillBlankTest {

	@Test
	public void testFillBlank() {
		// Similar quiz to question response, checks if the guaranteed interface methods work 
		String firstHalf = "The elephant is the largest land"; 
		String secondHalf = ".";
		FillBlankAnswer fba1 = new FillBlankAnswer("mammal");
		FillBlankAnswer fba2 = new FillBlankAnswer("lizard");
		FillBlank fb1 = new FillBlank(firstHalf, secondHalf, fba1);
		assertTrue(fb1.checkAnswer(fba1));
		assertTrue(!fb1.checkAnswer(fba2)); 
	}
	
	@Test
	public void testFillBlank2() {
		// Test if we accept different capitalizations 
		String firstHalf = "The"; 
		String secondHalf = "is the largest animal ever known to have lived.";
		FillBlankAnswer fba1 = new FillBlankAnswer("blue whale");
		FillBlankAnswer fba2 = new FillBlankAnswer("Blue Whale");
		FillBlankAnswer fba3 = new FillBlankAnswer("BLUE WHALE");
		FillBlankAnswer fba4 = new FillBlankAnswer("blu whale");
		FillBlank fb1 = new FillBlank(firstHalf, secondHalf, fba1);
		assertTrue(fb1.checkAnswer(fba1));
		assertTrue(fb1.checkAnswer(fba2));
		assertTrue(fb1.checkAnswer(fba3));
		assertTrue(!fb1.checkAnswer(fba4));
	}
}
