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
		IUserRepository userRepository = (UserRepository) request.getSession().getAttribute("user");
		String username = request.getParameter("username");
		String type = request.getParameter("messagetype");
		if (username.equals(userRepository.getUsername())) {
			request.setAttribute("status", "Sorry, you can't send a " + type + " to yourself!");
		} else if (userRepository.userExists(username)) {
			String content = request.getParameter("content");
			int userId = userRepository.usernameToId(username);
			String userLink = "<a href=\"/user/userProfile.jsp?username=" + userRepository.getUsername() + "\">" + userRepository.getUsername() + "</a>";
			if (type.equals("friend")) {
				content =  userLink + " would like to be friends.";
			} else if (type.equals("challenge")) {
				content = userLink + " has challenged you to take a quiz!</p>" +
								"<p><a href=\"/quiz/quizsummary.jsp?id=" + content + "\">Take the quiz</a></p>" +
								"<p>Their high score: " + userRepository.getQuizHighScore(Integer.parseInt(content)); 
			}
			Message msg = new Message(userId, userRepository.getUserId(), type, content);
			SocialRepository.addMessage(msg);
			request.setAttribute("status", "Sent!");
		} else {
			request.setAttribute("status", "That user does not exist.");
		}
		
		RequestDispatcher dispatch;
		if (type.equals("friend")) {
			dispatch = request.getRequestDispatcher("user/userProfile.jsp");
		} else if (type.equals("challenge")) {
			dispatch = request.getRequestDispatcher("quiz/quizsummary.jsp?id=" + request.getParameter("content"));
		} else {
			dispatch = request.getRequestDispatcher("user/messages.jsp");
		}
		dispatch.forward(request, response);

	}

}
