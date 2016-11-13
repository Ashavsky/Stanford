package user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by scottparsons on 2/27/16.
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserRepository userRepo = (UserRepository) request.getSession().getAttribute("user");
        String username = request.getParameter("inputUserName");
        String password = request.getParameter("inputPassword");
        String adminCheckbox = request.getParameter("adminCheckbox");
        String email = request.getParameter("inputEmail");

        if(userRepo.createNewUser(username, email, password, adminCheckbox != null)) {
            request.getSession().setAttribute("registerError","");
            RequestDispatcher dispatch = request.getRequestDispatcher("user/userHomePage.jsp");
            dispatch.forward(request, response);
        }
        else {
            request.getSession().setAttribute("registerError","Username already taken. Please try another.");
            //RequestDispatcher dispatch = request.getRequestDispatcher("docs/register/register.html");
            response.sendRedirect("user/register.jsp");
            //dispatch.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
