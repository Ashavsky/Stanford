<%@ page import="quiz.*, user.*, java.util.*" %>
<% IUserRepository user = (UserRepository) session.getAttribute("user"); %>
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

    <title>Quiz Home Page</title>

    <!-- Bootstrap core CSS -->
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/starter-template.css" rel="stylesheet">

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
            <a class="navbar-brand" href="#">QuizIt</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/user/userHomePage.jsp">Home</a></li>
                <li class="active"><a href="/quiz/quizhomepage.jsp">Quiz</a></li>
                <li><a href="/user/userFeed.jsp">Feed</a></li>
                <% if(user.isAdmin()) {
                    out.println("<li><a href='/admin/dashboard.jsp'>Admin</a></li>");
                }
                %>
                <li><a href="/user/messages.jsp">&#128172;</a></li>
            </ul>
            <form class="navbar-form navbar-right" action="/SignOutServlet" method="post">
                <button type="submit" class="btn btn-primary">Sign Out</button>
            </form>
            <form class="navbar-form navbar-right" action="/UserSearchServlet" method="post">
                <div class="form-group">
                    <input type="text" placeholder="&#128269; Search for User" class="form-control" name="username">
                </div>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>

<div class="container">
    <div class="starter-template">
        <h1>Welcome to QuizIt!</h1>
        <h2>Select a Quiz</h2>
        <a class="btn btn-primary" href="createquiz.jsp" role="button"> Create your own quiz!</a>
    </div>
    <div class="col-md-6 col-md-offset-3">

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Quiz</th>
                <th>Creator</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<QuizSummary> quizSummaries = QuizRepository.GetAllQuizSummaries();
                for (QuizSummary currentSummary: quizSummaries) {
                    int quizid = currentSummary.getQuizId();
                    String username = user.idToUsername(currentSummary.getCreatorId());
                    String absURL = "/user/userProfile.jsp?username=";
                    out.println("<tr>");
                    out.println("<td>" + "<a href=\"/quiz/quizsummary.jsp?id=" + quizid + "\">" + currentSummary.getQuizName() + "</a></td>");
                    out.println("<td>" + "<a href=\"" + absURL + username + "\">" + username + "</a></td>");
                    out.println("<td>" + currentSummary.getCreateDate() + "</td>");
                    out.print("</td>");
                }
            %>
            </tbody>
            <%--<li class='list-group-item' data-toggle='tooltip' title=""><a href="/quiz/quizsummary.jsp?id=<%=quizid %>"><%out.println(currentSummary.getQuizName());%></a></li>--%>
        </table>
    </div>


</div>

<!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>