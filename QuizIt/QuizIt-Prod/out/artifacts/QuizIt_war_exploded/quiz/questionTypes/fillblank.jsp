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
Quiz fillBlankQuiz = quizList.get(1);

%>
<title>Fill in the Blank</title>
</head>
<body>
<h1>Fill in the Blank</h1>
<form action="CheckAnswerServlet" method="post">
<input type="hidden" name="quizid" value=<%=fillBlankQuiz.getId()%>/>
<%
List<Question> quizQuestions = fillBlankQuiz.getQuestions(); 
for(Question currQuestion : quizQuestions) {
	String unparsedQ = currQuestion.toString(); 
	String parsedQ = unparsedQ.replace("|", "_____");
%>	
<%out.println(parsedQ);%>
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