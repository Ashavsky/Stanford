package quiz;

import user.IUserRepository;
import user.UserRepository;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Servlet implementation class CreateQuizServlet
 */
@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Question> createQuizQuestions = (ArrayList<Question>) getServletContext().getAttribute("createquestions");
		IUserRepository user = (UserRepository) request.getSession().getAttribute("user");

		int userId = user.getUserId(); // definitely need to remove this asap, where is userId
		String quizName = request.getParameter("quizname");
		String quizDescription = request.getParameter("quizdescription");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String createDate = formatter.format(new Date());

		QuizSummary summary = new QuizSummary(quizName, quizDescription, userId, createDate);
		int quizId = QuizRepository.AddQuizHeader(summary);
		QuizRepository.AddQuizContent(createQuizQuestions, quizId);

		createQuizQuestions = new ArrayList<>(); // Reset the quiz questions
		getServletContext().setAttribute("createquestions", createQuizQuestions);
		//TODO: May want to add a blurb to whatever quiz homepage that the quiz was successfully created
		RequestDispatcher rd = request.getRequestDispatcher("/quiz/quizhomepage.jsp");
		rd.forward(request, response);
	}

}
