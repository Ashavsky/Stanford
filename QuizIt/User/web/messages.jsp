<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, User.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Messages</title>
</head>
<body>
<h1>Messages</h1>
<%
IUserRepository userRepository = (UserRepository) session.getAttribute("user");
Set<Integer> friendReqs = Messaging.getFriendRequests(userRepository.getUserId());
Set<Integer> messages = Messaging.getMessages(userRepository.getUserId());
for (int id : friendReqs) {
	Message req = Messaging.getMessage(id);
%>
<p><%= req.getContent() %></p>
<form action="FriendServlet" method="post">
<input name="userId" type="hidden" value="<%= req.getSender() %>"/>
<input name="id" type="hidden" value="<%= id %>"/>
<p><input name="action" type="submit" value="Accept" />
<input name="action" type="submit" value="Deny" /></p>
</form>
<% 
} 

for (int id : messages) {
	Message msg = Messaging.getMessage(id);
	if (msg.getSender() == userRepository.getUserId()) {
%>
<p><b>To: <%= userRepository.idToUsername(msg.getRecipient()) %></b></p>
<% 
} else { 
%>
<p><b>From: <%= userRepository.idToUsername(msg.getSender()) %></b></p>
<%--<p><a href="message.jsp?id=">id</a></p>--%>
<% 
} 
%>
<p><%= msg.getContent() %></p>
<%
}
%>
<form action="MessageServlet" method="post">
<p>To: <input name="username" type="text"/>
<%
if (request.getAttribute("status") != null) {
%>
<p><%= (String) request.getAttribute("status") %></p>
<%
}
%>
<input name="messagetype" type="hidden" value="note"/>
<p><textarea name="content" rows="5" cols="20"></textarea>
<p><input type="submit" value="Send Message"/></p>
</form>
</body>
</html>