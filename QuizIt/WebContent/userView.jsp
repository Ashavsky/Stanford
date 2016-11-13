<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="user.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% 
User user = (User) request.getSession().getAttribute("user");
String username = request.getParameter("username"); 
%>
<title><%= username %></title>
</head>
<body>
<%@include file="userSearch.html" %>
<h1><%= username %></h1>
<% 
if (user.isFriends(username)) { 
%>
<p>Friends</p>
<% 
} else if (user.sentRequest(username)) { 
%>
<p>Friend request pending.</p>
<% 
} else { 
%>
<form action="MessageServlet" method="post">
<input name="username" type="hidden" value="<%= username %>"/>
<input name="messagetype" type="hidden" value="friend"/>
<input name="content" type="hidden" value=""/>
<input type="submit" value="Send Friend Request"/>
</form>
<% 
} 
%>
</body>
</html>