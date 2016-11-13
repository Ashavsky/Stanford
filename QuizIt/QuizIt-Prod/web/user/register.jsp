<%--
  Created by IntelliJ IDEA.
  User: scottparsons
  Date: 3/6/16
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Register for QuizIt</title>

    <!-- Bootstrap core CSS -->
    <link href="/../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/../css/register.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <form class="form-signin" action="/NewAccountServlet" method="post">
        <h1 class="form-group">Sign Up</h1>
        <div class="form-group">
            <label for="inputUserName">Username</label>
            <input type="name" class="form-control" id="inputUserName" placeholder="Username" name="inputUserName" required>
        </div>
        <!-- <div class="form-group">
          <label for="inputFirstName">First Name</label>
          <input type="name" class="form-control" id="inputFirstName" placeholder="First Name" name="inputFirstName">
        </div>
        <div class="form-group">
          <label for="inputLastName">Last Name</label>
          <input type="name" class="form-control" id="inputLastName" placeholder="Last Name" name="inputLastName">
        </div> -->
        <div class="form-group">
            <label for="inputEmail">Email address</label>
            <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="inputEmail" required>
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="InputPassword" placeholder="Password" name="inputPassword"
                   required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase
                  and lowercase letter, and at least 8 or more characters">
        </div>
        <div class="checkbox">
            <label><input type="checkbox" value="" name="adminCheckbox">Administrative Account</label>
        </div>
        <!--
      <div class="form-group">
        <label for="exampleInputFile">Upload Your Profile Picture</label>
        <input type="file" id="exampleInputFile">
        <p class="help-block">Smile!</p>
      </div> -->
        <p><%= session.getAttribute("registerError") %></p>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

