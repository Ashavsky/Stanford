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
Quiz multipleChoiceQuiz = quizList.get(2);

%>
<title>Multiple Choice</title>
</head>
<body>
<h1>Multiple Choice</h1>
<form action="CheckAnswerServlet" method="post">
<input type="hidden" name="quizid" value=<%=multipleChoiceQuiz.getId()%>/>
<%
List<Question> quizQuestions = multipleChoiceQuiz.getQuestions(); 
for(Question currQuestion : quizQuestions) {
	MultipleChoice mcq = (MultipleChoice) currQuestion; 
	List<Answer> answerChoices = mcq.getAnswerChoices();
%>	
<%out.println(currQuestion.toString());
for(Answer currAnswer: answerChoices) {
	 currAnswer.toString();
%>
<br>
<input type="radio" name="<%=currQuestion.getId()%>" id="<%=currAnswer.getId()%>" value="<%=currAnswer.toString()%>"> <%out.println(currAnswer.toString()); %> <br> 
<%
}
%>
<br>
<br>
<%
}
%>
<input type="submit" value="Submit">
</form>
</body>
</html>