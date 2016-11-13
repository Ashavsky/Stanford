package User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserRepository userRepository = (UserRepository) request.getSession().getAttribute("user");
		String username = request.getParameter("username");
		RequestDispatcher dispatch;
		if (username.equals(userRepository.getUsername())) {
			dispatch = request.getRequestDispatcher("userHome.jsp");
		} else if (userRepository.userExists(username)) {
			dispatch = request.getRequestDispatcher("userView.jsp");
		} else {
			dispatch = request.getRequestDispatcher("userNotFound.jsp");
		}
		dispatch.forward(request, response);
	}

}
