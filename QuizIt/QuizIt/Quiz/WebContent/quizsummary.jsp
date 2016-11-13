<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "quiz.*" %>
<%@ page import = "java.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
String quizIdStr = request.getParameter("id");
int quizId = Integer.parseInt(quizIdStr);
List<QuizSummary> quizSummaries = (ArrayList<QuizSummary>) getServletContext().getAttribute("quizsummary");
QuizSummary wantedSummary = null; 
for(QuizSummary currSummary: quizSummaries) {
	if(currSummary.getQuizId() == quizId) {
		wantedSummary = currSummary; 
	}
}
List<QuizStats> quizStatsTable =  (ArrayList<QuizStats>) getServletContext().getAttribute("quizstats");

QuizStats wantedStats = null; 
for(QuizStats currStats: quizStatsTable) {
	if(currStats.getQuizId() == quizId) {
		wantedStats = currStats; 
	}
}
%>
<title><%out.println(wantedSummary.getQuizName()); %></title>
</head>
<body>
<h1><%out.println(wantedSummary.getQuizName()); %></h1>
<h2><%out.println(wantedSummary.getQuizDescription()); %></h2>
<h2>Creator: <%out.println(wantedSummary.getCreatorId()); %></h2>
<h2>Total Attempts: <%out.println(wantedStats.getQuizAttempts()); %></h2>
<a href="takequiz.jsp?id=<%=wantedSummary.getQuizId() %>">Take Quiz</a>
<h2>Quiz Options</h2>
<p></p>
</body>
</html>