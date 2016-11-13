package user;

import db.UserPersistence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by scottparsons on 3/7/16.
 */
@WebServlet("/PromoteUserServlet")
public class PromoteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        IUserRepository userRepo = (UserRepository) request.getSession().getAttribute("user");
        String username = request.getParameter("inputUserName");

        if(userRepo.userExists(username)) {
            if(!userRepo.isAdmin(UserPersistence.GetUser(username).userName)) {
                userRepo.promoteToAdmin(username);
                out.print(username + " successfully promoted to administrator");
            }
            else {
                out.print(username + " is already an administrator.");
            }
        }
        else {
            out.print(username + " not found. Please try another user.");
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
