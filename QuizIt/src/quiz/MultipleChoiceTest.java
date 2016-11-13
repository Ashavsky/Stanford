package quiz;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MultipleChoiceTest {

	@Test
	public void testMultipleChoice() {
		// For now, we'll just store the letter as the choice strings
		String question = "Which of these organisms is considered a fungus?";
		List<String> choices = new ArrayList<String>();
		choices.add("A");
		choices.add("B");
		choices.add("C");
		MultipleChoiceAnswer correctChoice = new MultipleChoiceAnswer("B");
		MultipleChoiceAnswer wrongChoice = new MultipleChoiceAnswer("C");
		MultipleChoice mc1 = new MultipleChoice(question, correctChoice, choices);
		assertTrue(mc1.checkAnswer(correctChoice));
		assertTrue(!mc1.checkAnswer(wrongChoice));
	}

}
