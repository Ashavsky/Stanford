<%--
  Created by IntelliJ IDEA.
  User: scottparsons
  Date: 2/27/16
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
<h1>Create New Account</h1>
<p>Please enter proposed name and password.
</p>
<p><%= session.getAttribute("createInfo") %></p>
<form action="NewAccountServlet" method="post">
    <p>User Name: <input type="text" name="username" /></p>
    <p>Password: <input type="password" name="password" /><br>
        <input type="checkbox" name="adminCheckbox" value="Admin">Admin Account?<br>
        <input type="submit" value="Login"/></p>
</form>
</body>
</html>
