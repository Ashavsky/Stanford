<%--
  Created by IntelliJ IDEA.
  User: Larry268
  Date: 3/10/2016
  Time: 12:34 AM
  This is a test file to see if the picture response front end is implemented properly
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "quiz.*" %>
<%@ page import = "java.util.*" %>
<html>
<head>
    <%
        List<Quiz> quizList = (List<Quiz>) getServletContext().getAttribute("quizlist");
        Quiz pictureResponseQuiz = quizList.get(4);

    %>
    <title>Picture Response</title>
</head>
<body>
    <form action="../CheckAnswerServlet" method="post">
        <input type="hidden" name="quizid" value=<%=pictureResponseQuiz.getId()%>/>
        <%
            List<Question> quizQuestions = pictureResponseQuiz.getQuestions();
            for(Question currQuestion : quizQuestions) {
                PictureResponse currPictureResponse = (PictureResponse) currQuestion;
                String imageURL = currPictureResponse.getImageURL();
                String questionStr = currPictureResponse.toString();
        %>

        <img src="<%=imageURL%>" />
        <br>
        <%out.println(currPictureResponse.toString());%>
        <br>
        <input type="text" name="<%=currPictureResponse.getId() %>">
        <br>
        <%
            }
        %>
        <input type="submit" value="Submit">
    </form>

</body>
</html>
