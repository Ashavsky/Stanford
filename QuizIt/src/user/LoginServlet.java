package user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by scottparsons on 2/25/16.
 */
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(user.isCorrectLogin(username, password)) {
            user.setUsername(username);
            RequestDispatcher dispatch = request.getRequestDispatcher("userHome.jsp");
            dispatch.forward(request, response);
        }
        else {
            RequestDispatcher dispatch = request.getRequestDispatcher("Incorrect.html");
            dispatch.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
