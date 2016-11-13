package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Servlet implementation class CheckAnswer
 */
@WebServlet("/CheckAnswerServlet")
public class CheckAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAnswerServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handles scoring for text/string based answers
		// Iterate through the list of questions to return a score
		String attemptIdStr = request.getParameter("attemptid"); 
		String parsedAttemptId = attemptIdStr.replaceAll("\\/", "");
		int attemptId = Integer.parseInt(parsedAttemptId);
		List<QuizAttemptHistory> quizAttemptHistoryTable = (ArrayList<QuizAttemptHistory>) getServletContext().getAttribute("quizattempts");
		QuizAttemptHistory wantedAttempt = null; 
		for(QuizAttemptHistory currentAttempt : quizAttemptHistoryTable) {
			if(currentAttempt.getAttemptId() == attemptId) {
				wantedAttempt = currentAttempt; 
			}
		}
		wantedAttempt.endAttempt();
		
		String idStr = request.getParameter("quizid");
		String parsedId = idStr.replaceAll("\\/", "");
		int quizid = Integer.parseInt(parsedId);
		
		List<QuizStats> quizStatsTable = (ArrayList<QuizStats>) getServletContext().getAttribute("quizstats");
		QuizStats wantedStats = null; 
		for(QuizStats currStats: quizStatsTable) {
			if(currStats.getQuizId() == quizid) {
				wantedStats = currStats; 
			}
		}
		wantedStats.incrementQuizAttempts();
		//wantedStats.incrementUserAttempts(); IMPLEMENT WHEN USER GETS INTEGRATED 
		
		List<Quiz> quizList = (ArrayList<Quiz>) getServletContext().getAttribute("quizlist");
		Quiz quiz = quizList.get(quizid);
		List<Question> questions = quiz.getQuestions();
		int numPossible = questions.size();
		int totalCorrect = 0;
		for (Question currQuestion : questions) {
			String currId = Integer.toString(currQuestion.getId());
			String currAnswer = request.getParameter(currId);
			if (currAnswer != null && currQuestion.checkAnswer(currAnswer)) {
				totalCorrect++;
			}

		}
		wantedAttempt.setAttemptScore(totalCorrect);
		wantedStats.addSumActualScores(totalCorrect);
		wantedStats.addSumPossibleScores(numPossible);
		request.setAttribute("possible", numPossible);
		request.setAttribute("correct", totalCorrect);
		RequestDispatcher rd = request.getRequestDispatcher("quizresults.jsp");
		rd.forward(request, response);

	}

}
