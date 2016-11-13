package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String username = request.getParameter("username");
		String type = request.getParameter("messagetype");
		if (User.userExists(username)) {
			String content = request.getParameter("content");
			if (type.equals("friend")) {
				content = user.getUsername() + " would like to be friends.";
				user.addFriendRequest(username);
			} else if (type.equals("challenge")) {
				content = user.getUsername() + " has challenged you to take a quiz!</p>" +
								"<p><a href=\"quiz.jsp?id=\">" + content + "</a></p>" +
								"<p>Their high score: " + user.getQuizHighScore(content); 
			}
			Message msg = new Message(user.getUsername(), username, type, content);
			Messaging.addMessage(msg);
			request.setAttribute("status", "Sent!");
		} else {
			request.setAttribute("status", "That user does not exist.");
		}
		
		RequestDispatcher dispatch;
		if (type.equals("friend")) {
			dispatch = request.getRequestDispatcher("userView.jsp");
		} else if (type.equals("challenge")) {
			dispatch = request.getRequestDispatcher("quiz.jsp");
		} else {
			dispatch = request.getRequestDispatcher("messages.jsp");
		}
		dispatch.forward(request, response);

	}

}
