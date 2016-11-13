<%--
  Created by IntelliJ IDEA.
  User: scottparsons
  Date: 2/28/16
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,user.*" %>
<% User user = (User) session.getAttribute("user"); %>
<html>
<head>
    <title>Admin Options</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<h1><%= user.getUsername() %> Admin Page</h1>
<div class="container">
    <h2>Remove User</h2>
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Username
            <span class="caret"></span></button>
        <ul class="dropdown-menu">
            <%
                for(String s : user.getAllUsers()) {
                    out.println("<li><a href=''>" + s + "</a></li>");
                }
            %>
        </ul>
    </div>
</div>
<h2>Site Statistics</h2>
<p>Number of users: <%= user.getNumberOfUsers() %></p>
</body>
</html>
