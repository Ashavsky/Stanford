package quiz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Application Lifecycle Listener implementation class SampleQuizListener
 *
 */
@WebListener
public class SampleQuizListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public SampleQuizListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)  {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0)  {
		List<Quiz> quizList = new ArrayList<Quiz>();
		List<QuizStats> quizStatsTable = new ArrayList<QuizStats>(); // Simulates the quizStats table
		List<QuizSummary> quizSummaryTable = new ArrayList<QuizSummary>();
		List<QuizAttempt> quizAttemptTable = new ArrayList<QuizAttempt>();

		List<Question> questions = new ArrayList<Question>();

		String q1 = "What is 2+2?";
		List<Answer> possibleAnswers = new ArrayList<Answer>();
		QResponseAnswer qra1 = new QResponseAnswer(new HashSet<String>(Arrays.asList("4")), 0);
		possibleAnswers.add(qra1);
		QResponse qr1 = new QResponse(q1, possibleAnswers, 1, false, 1);

		String q2 = "What is 3+3?";
		Set<Answer> answers2 = new HashSet<Answer>();
		QResponseAnswer qra2 = new QResponseAnswer(new HashSet<String>(Arrays.asList("6")), 1);
		answers2.add(qra2);
		QResponse qr2 = new QResponse(q2, possibleAnswers, 1, false, 2);

		String q3 = "What is 4+4?";
		Set<Answer> answers3 = new HashSet<Answer>();
		QResponseAnswer qra3 = new QResponseAnswer(new HashSet<String>(Arrays.asList("8")), 2);
		answers3.add(qra3);
		QResponse qr3 = new QResponse(q3, possibleAnswers, 1, false, 3);

		questions.add(qr1);
		questions.add(qr2);
		questions.add(qr3);
		Quiz qResponseQuiz = new Quiz(questions, 0);
		quizList.add(qResponseQuiz);
		QuizStats quiz1Stats = new QuizStats(0);
		quizStatsTable.add(quiz1Stats);
		String quiz1Name = "Math Quiz";
		String quiz1Description = "An assortment of math quesitons.";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		//QuizSummary quiz1Summary = new QuizSummary(quiz1Name, quiz1Description, 1,formatter.format(new Date()) );
		QuizSummary quiz1Summary = new QuizSummary(quiz1Name, quiz1Description, 1,formatter.format(new Date()), 0);
		quizSummaryTable.add(quiz1Summary);

		List<Question> questions2 = new ArrayList<Question>();
		String fbq1 = "The | is the largest animal ever known to have lived.";
		FillBlankAnswer fba1 = new FillBlankAnswer("blue whale", 0);
		Set<Answer> fbAnswers1 = new HashSet<Answer>();
		fbAnswers1.add(fba1);
		FillBlank fb1 = new FillBlank(fbq1, fbAnswers1, 0);
		questions2.add(fb1);
		Quiz fillBlankQuiz = new Quiz(questions2, 1);
		quizList.add(fillBlankQuiz);
		QuizStats quiz2Stats = new QuizStats(1);
		quizStatsTable.add(quiz2Stats);
		String quiz2Name = "Biology Quiz";
		String quiz2Description = "This quiz tests your knowledge about all things biology";
		//QuizSummary quiz2Summary = new QuizSummary(quiz2Name, quiz2Description, 2,formatter.format(new Date()) );
		QuizSummary quiz2Summary = new QuizSummary(quiz2Name, quiz2Description, 2,formatter.format(new Date()) , 1);
		quizSummaryTable.add(quiz2Summary);

		List<Question> multipleChoiceQuestions = new ArrayList<Question>();
		String multipleChoiceQ = "What year is John Carlo?";
		List<Answer> answerChoices = new ArrayList<Answer>();
		MultipleChoiceAnswer correctChoice = new MultipleChoiceAnswer("Junior", 0);
		MultipleChoiceAnswer wrongChoice = new MultipleChoiceAnswer("Sophomore", 1);
		MultipleChoiceAnswer wrongChoice2 = new MultipleChoiceAnswer("Freshman", 2);
		answerChoices.add(correctChoice);
		answerChoices.add(wrongChoice);
		answerChoices.add(wrongChoice2);
		Set<Answer> correctAnswers = new HashSet<Answer>(Arrays.asList(correctChoice));
		MultipleChoice mc1 = new MultipleChoice(multipleChoiceQ, correctAnswers, answerChoices, false, 4);
		multipleChoiceQuestions.add(mc1);

		Quiz multipleChoiceQuiz = new Quiz(multipleChoiceQuestions, 2);
		quizList.add(multipleChoiceQuiz);
		QuizStats quiz3Stats = new QuizStats(2);
		quizStatsTable.add(quiz3Stats);
		String quiz3Name = "Random Facts";
		String quiz3Description = "The theme of this quiz is random trivia!";
		QuizSummary quiz3Summary = new QuizSummary(quiz3Name, quiz3Description, 1,formatter.format(new Date()), 2);
		quizSummaryTable.add(quiz3Summary);

		List<Question> complexQuestions = new ArrayList<Question>();

		String complexQ1Str = "Why is it called ebay?";
		List<Answer> complexAnswerChoices = new ArrayList<Answer>();
		MultipleChoiceAnswer correctComplexChoice = new MultipleChoiceAnswer("It was owned by a company with that name.", 0);
		MultipleChoiceAnswer wrongComplexChoice = new MultipleChoiceAnswer("Completely random words.", 1);
		MultipleChoiceAnswer wrongComplexChoice2 = new MultipleChoiceAnswer("They used a random name generator", 2);
		complexAnswerChoices.add(correctComplexChoice);
		complexAnswerChoices.add(wrongComplexChoice);
		complexAnswerChoices.add(wrongComplexChoice2);
		Set<Answer> complexCorrectAnswers = new HashSet<Answer>(Arrays.asList(correctComplexChoice));
		MultipleChoice complexQ1 = new MultipleChoice(complexQ1Str, complexCorrectAnswers,complexAnswerChoices, false, 3);
		complexQuestions.add(complexQ1);
		String complexQ2Str = "The | is a fruit with 92 percent water content.";
		FillBlankAnswer complexFBA1 = new FillBlankAnswer("watermelon", 0);
		FillBlankAnswer complexFBA2 = new FillBlankAnswer("strawberry", 1);
		Set<Answer> complexFBAnswers = new HashSet<Answer>();
		complexFBAnswers.add(complexFBA1);
		complexFBAnswers.add(complexFBA2);
		FillBlank complexQ2 = new FillBlank(complexQ2Str, complexFBAnswers, 0);
		complexQuestions.add(complexQ2);
		Quiz complexQuiz = new Quiz(complexQuestions, 3);
		quizList.add(complexQuiz);
		QuizStats complexQuizStats = new QuizStats(3);
		quizStatsTable.add(complexQuizStats);
		String complexQuizName = "Complex Quiz";
		String complexQuizDescription = "A quiz with different question types!";
		//QuizSummary complexQuizSummary = new QuizSummary(complexQuizName, complexQuizDescription, 1,formatter.format(new Date()) );
		QuizSummary complexQuizSummary = new QuizSummary(complexQuizName, complexQuizDescription, 1,formatter.format(new Date()), 3);
		quizSummaryTable.add(complexQuizSummary);

		// Test code for Picture Response
		List <Question> pictureResponseQuestions = new ArrayList<Question>();
		String picture1QStr = "What is this church's nickname?";
		String picture1QURL = "http://events.stanford.edu/events/252/25201/Memchu_small.jpg";
		String picture1AnswerStr = "MemChu";
		PictureResponseAnswer picture1Answer = new PictureResponseAnswer(picture1AnswerStr, 0);
		PictureResponse picture1 = new PictureResponse(picture1QStr, picture1QURL, picture1Answer, 0);
		pictureResponseQuestions.add(picture1);
		String picture2QStr = "What is the name of this fish?";
		String picture2QURL = "https://pbs.twimg.com/profile_images/681579053338394624/8UZsho0J.jpg";
		String picture2AnswerStr = "Dory";
		PictureResponseAnswer picture2Answer = new PictureResponseAnswer(picture2AnswerStr, 1);
		PictureResponse picture2 = new PictureResponse(picture2QStr, picture2QURL, picture2Answer, 1);
		pictureResponseQuestions.add(picture2);
		Quiz pictureResponseQuiz = new Quiz(pictureResponseQuestions, 4);
		quizList.add(pictureResponseQuiz);
		QuizStats pictureQuizStats = new QuizStats(4);
		quizStatsTable.add(pictureQuizStats);
		String pictureName = "Picture Quiz";
		String pictureQuizDescription = "A quiz filled with picture response questions!";
		//QuizSummary pictureQuizSummary = new QuizSummary(pictureName, pictureQuizDescription, 2, formatter.format(new Date()));
		QuizSummary pictureQuizSummary = new QuizSummary(pictureName, pictureQuizDescription, 2, formatter.format(new Date()), 4);
		quizSummaryTable.add(pictureQuizSummary);

		arg0.getServletContext().setAttribute("quizlist", quizList);
		arg0.getServletContext().setAttribute("quizstats", quizStatsTable);
		arg0.getServletContext().setAttribute("quizsummary", quizSummaryTable);
		arg0.getServletContext().setAttribute("quizattempts", quizAttemptTable);

		List<Question> createQuestionsList = new ArrayList<Question>(); // Used in quiz creation
		arg0.getServletContext().setAttribute("createquestions", createQuestionsList);
	}

}