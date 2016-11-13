package user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by scottparsons on 3/1/16.
 */
@WebServlet(name = "TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String quizId = request.getParameter("quizid");
        Double score = Double.parseDouble(request.getParameter("score"));
        user.addQuizScore(quizId, score);

        RequestDispatcher dispatch = request.getRequestDispatcher("userHome.jsp");
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
