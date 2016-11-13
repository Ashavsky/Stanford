<%--
  Created by IntelliJ IDEA.
  User: scottparsons
  Date: 3/6/16
  Time: 10:36 AM
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

    <title>Welcome to QuizIt</title>

    <!-- Bootstrap core CSS -->
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/homepage.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.jsp">QuizIt</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" onsubmit="event.preventDefault(); loginUser()">
                <span style="color:#ff4341;" id="loginError"></span>
                <div class="form-group">
                    <input type="text" placeholder="Username" class="form-control" id="userName">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" id="password">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>Welcome to QuizIt!</h1>
        <p>Are you ready for a challenge? Great at trivia? Join a community of awesome quiztakers at QuizIt.</p>
        <p><a class="btn btn-primary btn-lg" href="/user/register.jsp" role="button">Sign Up</a>

    </div>
</div>

<div class="container">

    <footer>
        <p>CS 108 Final Project</p>
    </footer>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
<script type="text/javascript">
    function loginUser() {
        var username = document.getElementById("userName").value;
        var password= document.getElementById("password").value;

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                if (xhttp.responseText.indexOf("Invalid") == 0) {
                    document.getElementById("loginError").innerHTML = xhttp.responseText;
                    document.getElementById("userName").value = "";
                    document.getElementById("password").value = "";
                }
                else {
                    location.href = "/user/userHomePage.jsp";
                }
            }
        };
        xhttp.open("POST", "/LoginServlet", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
        xhttp.send("userName=" + username + "&password=" + password);
    }
</script>
</body>
</html>

