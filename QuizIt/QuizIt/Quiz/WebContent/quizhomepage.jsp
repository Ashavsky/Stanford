<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "quiz.*" %>
<%@ page import = "java.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Homepage</title>
</head>
<body>
<h1>Welcome to QuizIt!</h1>
<h2>Select a Quiz</h2>
<%
List<QuizSummary> quizSummaries = (List<QuizSummary>) getServletContext().getAttribute("quizsummary");
for (QuizSummary currentSummary: quizSummaries) {
	
%>
<li><a href="quizsummary.jsp?id=<%=currentSummary.getQuizId() %>"><%out.println(currentSummary.getQuizName());%></a></li>
<%
}
%>
</body>
</html>