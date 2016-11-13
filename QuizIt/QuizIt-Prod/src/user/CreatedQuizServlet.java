package user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by scottparsons on 2/28/16.
 */
@WebServlet("/CreatedQuizServlet")
public class CreatedQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserRepository userRepo = (UserRepository) request.getSession().getAttribute("user");
        String quizId = request.getParameter("quizid");
//        userRepo.addCreatedQuiz(quizId);

        RequestDispatcher dispatch = request.getRequestDispatcher("user/userHomePage.jsp");
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
