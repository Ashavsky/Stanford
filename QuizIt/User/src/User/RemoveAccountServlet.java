package User;

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
 * Created by scottparsons on 3/6/16.
 */
@WebServlet(name = "RemoveAccountServlet")
public class RemoveAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        IUserRepository userRepo = (UserRepository) request.getSession().getAttribute("user");
        String username = request.getParameter("inputUserName");

        if(userRepo.userExists(username)) {
            DatabaseTasks.DeleteUserDetail(username);
            out.print(username + " successfully removed");
        }
        else {
            out.print(username + " not found. Please try another user.");
        }
        out.close();
        //response.sendRedirect("docs/admin/remove_user.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
