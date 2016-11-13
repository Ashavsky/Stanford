package user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by scottparsons on 2/25/16.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserRepository userRepo = (UserRepository) request.getSession().getAttribute("user");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        if(userRepo.isCorrectLogin(username, password))
        {
            userRepo.PopulateCurrentUser(username); //will be just userId and populate the currentUser object the repo will have
            RequestDispatcher dispatch = request.getRequestDispatcher("/user/userHomePage.jsp");
            dispatch.forward(request, response);
        }
        else {
            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter out = response.getWriter();
            out.print("Invalid username or password. Please try again.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
