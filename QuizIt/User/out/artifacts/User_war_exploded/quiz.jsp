<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% String id = request.getParameter("id"); %>
<title>Quiz <%= id %></title>
</head>
<body>
<h1>Quiz <%= id %></h1>
<form action="MessageServlet" method="post">
<p>Challenge a friend! <input type="text" name="username" />
<input name="messagetype" type="hidden" value="challenge"/>
<input name="content" type="hidden" value=<%= id %>/>
<input type="submit" value="Send Challenge" /></p>
</form>
<%
if (request.getAttribute("status") != null) {
%>
<p><%= (String) request.getAttribute("status") %></p>
<%
}
%>
</body>
</html>