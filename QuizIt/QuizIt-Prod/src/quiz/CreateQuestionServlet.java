package quiz;

import user.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateQuestionServlet
 */
@WebServlet("/CreateQuestionServlet")
public class CreateQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateQuestionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String questionType = request.getParameter("questiontype");
		String lastQuestionType = request.getParameter("lastquestiontype");
		List<Question> createQuizQuestions = (ArrayList<Question>) getServletContext().getAttribute("createquestions");

		if (lastQuestionType != null)
		{
			if (lastQuestionType.equals("qresponse") || lastQuestionType.equals("fillblank"))
			{
				List<String> listAnswerStrings = Answer.answerToList(request.getParameter("answer").toLowerCase());
				List<Answer> listAnswers = new ArrayList<>();

				String questionStr = request.getParameter("question");
				Question question = new Question(0, lastQuestionType, questionStr); //quizId overwritten later anyway

				for(String answerText : listAnswerStrings)
					listAnswers.add(new Answer(0, lastQuestionType, answerText, true)); //questionId overwritten later anyway
				question.setAnswers(listAnswers);
				createQuizQuestions.add(question);
			}
			else if(lastQuestionType.equals("multiplechoice"))
			{
				List<Answer> listAnswers = new ArrayList<>();

			    String questionStr = request.getParameter("question");
				Question question = new Question(0, lastQuestionType, questionStr);

			    String answerText = request.getParameter("answer").toLowerCase();
				listAnswers.add(new Answer(0, lastQuestionType, answerText, true));

				int wrongChoices = 3; // Magic number defined in js file/design (decides how many more choices to include)
				for(int i = 0; i < wrongChoices; i++) {
					String wrongAnswerText = request.getParameter("wrong"+i);
					listAnswers.add(new Answer(0, lastQuestionType, wrongAnswerText, false));
				}

				question.setAnswers(listAnswers);
				createQuizQuestions.add(question);
			}
			else if (lastQuestionType.equals("pictureresponse"))
			{
				List<String> listAnswerStrings = Answer.answerToList(request.getParameter("answer").toLowerCase());
				List<Answer> listAnswers = new ArrayList<>();

				String questionStr = request.getParameter("question");
				String imageURL = request.getParameter("imageurl");
				Question question = new Question(0, lastQuestionType, questionStr + ";" + imageURL); //quizId overwritten later anyway

				for(String answerText : listAnswerStrings)
					listAnswers.add(new Answer(0, lastQuestionType, answerText, true)); //questionId overwritten later anyway
				question.setAnswers(listAnswers);
				createQuizQuestions.add(question);
			}
		}

		request.setAttribute("lastquestiontype", questionType);
		request.setAttribute("createquestions", createQuizQuestions);

		String addAnotherQuestion = request.getParameter("add");
		String completeQuiz = request.getParameter("complete");
		if (addAnotherQuestion != null) {
			RequestDispatcher rd = request.getRequestDispatcher("quiz/quizquestion.jsp");
			rd.forward(request, response);
		} else if (completeQuiz != null) {
			RequestDispatcher rd = request.getRequestDispatcher("quiz/finishcreatequiz.jsp");
			rd.forward(request, response);
		}

	}

}
