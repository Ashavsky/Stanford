package quiz;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MultipleChoiceTest {

	@Test
	public void testMultipleChoice() {
		// For now, we'll just store the letter as the choice strings
		String question = "What year is John Carlo?";
		List<Answer> answerChoices = new ArrayList<Answer>(); 
		MultipleChoiceAnswer correctChoice = new MultipleChoiceAnswer("Junior", 0);
		MultipleChoiceAnswer wrongChoice = new MultipleChoiceAnswer("Sophomore", 1);
		MultipleChoiceAnswer wrongChoice2 = new MultipleChoiceAnswer("Freshman", 2);
		answerChoices.add(correctChoice);
		answerChoices.add(wrongChoice);
		answerChoices.add(wrongChoice2);
		Set<Answer> correctAnswers = new HashSet<Answer>(Arrays.asList(correctChoice));
		MultipleChoice mc1 = new MultipleChoice(question, correctAnswers, answerChoices, false, 0);
		assertTrue(mc1.checkAnswer(correctChoice));
		assertTrue(!mc1.checkAnswer(wrongChoice));
	}

}
