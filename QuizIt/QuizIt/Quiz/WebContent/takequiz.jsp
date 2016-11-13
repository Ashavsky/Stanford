<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="quiz.*"%>
<%@ page import="java.util.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String quizIdStr = request.getParameter("id");
	int quizId = Integer.parseInt(quizIdStr);
	List<QuizSummary> quizSummaries = (List<QuizSummary>) getServletContext().getAttribute("quizsummary");
	QuizSummary wantedSummary = null;
	for (QuizSummary currSummary : quizSummaries) {
		if (currSummary.getQuizId() == quizId) {
			wantedSummary = currSummary;
		}
	}
	List<QuizAttemptHistory> quizAttemptHistoryTable = (List<QuizAttemptHistory>) getServletContext().getAttribute("quizattempts");
	int totalAttempts = quizAttemptHistoryTable.size(); 
	int dummyUserId = 5; 
	List<Quiz> quizList = (List<Quiz>) getServletContext().getAttribute("quizlist");
	Quiz quiz = quizList.get(quizId);
	List<Question> quizQuestions = quiz.getQuestions();
	QuizAttemptHistory currentAttempt = new QuizAttemptHistory(totalAttempts+1, quizId, dummyUserId, quizQuestions.size()); 
	currentAttempt.startAttempt();
	quizAttemptHistoryTable.add(currentAttempt); 
%>
<title>
	<%
		out.println(wantedSummary.getQuizName());
	%>
</title>
</head>
<body>
	<h1>
		<%
			out.println(wantedSummary.getQuizName());
		%>
	</h1>
	<form action="CheckAnswerServlet" method="post">
		<input type="hidden" name="quizid"
			value=<%=wantedSummary.getQuizId()%> />
		<input type="hidden" name="attemptid" value=<%=totalAttempts+1 %>/>
		<%
			for (Question currQuestion : quizQuestions) {
				String questionType = currQuestion.getQuestionType();
				if (questionType.equals("qresponse")) {
		%>
		<br>
		<%
			out.println(currQuestion.toString());
		%>
		<br> <input type="text" name="<%=currQuestion.getId()%>">
		<br>
		<%
			} else if (questionType.equals("fillblank")) {
					String unparsedQ = currQuestion.toString();
					String parsedQ = unparsedQ.replace("|", "_____");
		%>
		<%
			out.println(parsedQ);
		%>
		<br> <input type="text" name="<%=currQuestion.getId()%>">
		<br>
		<%
			} else if (questionType.equals("multiplechoice")) {
					MultipleChoice mcq = (MultipleChoice) currQuestion;
					List<Answer> answerChoices = mcq.getAnswerChoices();
					for (Answer currAnswer : answerChoices) {
		%>
		<br> <input type="radio" name="<%=currQuestion.getId()%>"
			id="<%=currAnswer.getId()%>" value="<%=currAnswer.toString()%>">
		<%
			out.println(currAnswer.toString());
		%>
		<br>
		<%
			}
		%>

		<%
			} else if (questionType.equals("pictureresponse")) {
		%>
		<%
			}
		%>
		<br>
		<%
			}
		%>
	<input type="submit" value="Submit">
	</form>
</body>
</html>