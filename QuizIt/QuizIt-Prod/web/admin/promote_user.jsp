<%--
  Created by IntelliJ IDEA.
  User: scottparsons
  Date: 3/6/16
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page import="java.util.*,user.*" %>
<% IUserRepository user = (UserRepository) session.getAttribute("user"); %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>QuizIt Admin Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="dashboard.jsp">QuizIt Admin Dashboard</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-success" href="/user/userHomePage.jsp" role="button">Home</a></li>
                <form class="navbar-form navbar-right" action="/SignOutServlet" method="post">
                    <button type="submit" class="btn btn-primary">Sign Out</button>
                </form>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/admin/dashboard.jsp">Overview</a></li>
                <li><a href="/admin/create_announcement.jsp">Create Announcement</a></li>
                <li><a href="/admin/remove_user.jsp">Remove User Account</a></li>
                <li><a href="remove_quiz.jsp">Remove Quiz</a></li>
                <li class="active"><a href="/admin/promote_user.jsp">Promote User</a><span class="sr-only">(current)</span></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Promote User</h1>

            <form onsubmit="event.preventDefault(); promote_user()">
                <div class="form-group">
                    <label for="inputUserName">Promote User</label>
                    <input type="name" class="form-control" id="inputUserName" placeholder="Username" name="inputUserName" required>
                    <p id="promoteUserText"></p>
                </div>
            </form>

            <h2 class="sub-header">Users</h2>
            <div class="table-responsive" id="userTable">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>UserId</th>
                        <th>Username</th>
                        <th>E-mail</th>
                        <th>Date Created</th>
                        <th>User Type</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<User> users = user.getAllUsers();
                        for(User u : users) {
                            out.println("<tr>");
                            out.println("<td>" + u.userId + "</td>");
                            out.println("<td>" + u.userName + "</td>");
                            out.println("<td>" + u.email + "</td>");
                            out.println("<td>" + u.dateCreated + "</td>");
                            if(user.isAdmin(u.userName)) {
                                out.println("<td>Admin</td>");
                            }
                            else {
                                out.println("<td>Standard</td>");
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="../assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../assets/js/ie10-viewport-bug-workaround.js"></script>

<script type="text/javascript">
    function promote_user() {
        var username = document.getElementById("inputUserName").value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                document.getElementById("inputUserName").va
                document.getElementById("promoteUserText").innerHTML = xhttp.responseText;
            }
        };
        xhttp.open("POST", "/PromoteUserServlet", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
        xhttp.send("inputUserName=" + username);
    }
</script>

</body>
</html>
