<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Results</title>
</head>
<body>
<%
int totalCorrect = (int) request.getAttribute("correct");
int numPossible = (int) request.getAttribute("possible");
%>
<h1>Quiz Results</h1>
<p> You got <%out.println(totalCorrect); %> correct out of <%out.println(numPossible); %> </p>
<a href="quizhomepage.jsp">Return to homepage</a>
</body>
</html>