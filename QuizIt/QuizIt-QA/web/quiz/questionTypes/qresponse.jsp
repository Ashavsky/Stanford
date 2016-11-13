<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "quiz.*" %>
<%@ page import = "java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
List<Quiz> quizList = (List<Quiz>) getServletContext().getAttribute("quizlist");
Quiz qResponseQuiz = quizList.get(0);

%>
<title>Question Response</title>
</head>
<body>
<h1>Question Response</h1>
<form action="CheckAnswerServlet" method="post">
<input type="hidden" name="quizid" value=<%=qResponseQuiz.getId()%>/>
<%
List<Question> quizQuestions = qResponseQuiz.getQuestions(); 
for(Question currQuestion : quizQuestions) {
%>	
<%out.println(currQuestion.toString());%>
<br>
<input type="text" name="<%=currQuestion.getId() %>">
<br>
<%
}
%>
<input type="submit" value="Submit">
</form>
</body>
</html>