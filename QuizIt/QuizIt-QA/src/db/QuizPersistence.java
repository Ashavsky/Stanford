package db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import quiz.*;
import user.Activity;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 3/9/2016.
 */
public class QuizPersistence
{
    public static int InsertQuizSummary(QuizSummary qz)
    {
        int quizId = 0;
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO QuizSummary VALUES(");
            sb.append("null,");
            sb.append("'" + qz.getQuizName()+ "',");
            sb.append("'" + qz.getQuizDescription()+ "',");
            sb.append(qz.getCreatorId() + ",");
            sb.append("'" + formatter.format(new Date()) + "');");
            DatabaseTasks.ExecuteUpdate(sb.toString());

            String query = String.format("Select * from QuizSummary WHERE %1$s = %2$s order by QuizId desc LIMIT 1;", "CreatorId", qz.getCreatorId());
            ResultSet rs = DatabaseTasks.GetResultSet(query);
            rs.next(); // exactly one result so allowed
            quizId = rs.getInt(1);

            db.UserPersistence.InsertUserActivity(qz.getCreatorId(), "QuizCreated", quizId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return quizId;
    }

    public static int InsertQuestion(Question question)
    {
        int questionId = 0;
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO QuizQuestions VALUES ");

            sb.append(" (null,");
            sb.append(question.getQuizId() + ",");
            sb.append("'" + question.getQuestionType() + "',");
            sb.append("'" + question.getQuestionText() + "');");
            DatabaseTasks.ExecuteUpdate(sb.toString());

            String query = String.format("Select * from QuizQuestions WHERE %1$s = %2$s order by QuestionId desc LIMIT 1;", "QuizId", question.getQuizId());
            ResultSet rs = DatabaseTasks.GetResultSet(query);
            rs.next(); // exactly one result so allowed
            questionId = rs.getInt(1);
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return questionId;
    }

    public static void InsertQuestions(List<Question> questions)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO QuizQuestions VALUES ");

        for(int i =0; i < questions.size(); i++)
        {
            Question question = questions.get(i);
            sb.append(" (");
            sb.append("null,");
            sb.append(question.getQuizId() + ",");
            sb.append("'" + question.getQuestionType() + "',");
            sb.append("'" + question.getQuestionText() + "')");

            if(i!=questions.size()-1)
                sb.append(",");
        }
        sb.append(";");
        DatabaseTasks.ExecuteUpdate(sb.toString());
    }

    public static void InsertAnswers(List<Answer> answers)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO QuizAnswers VALUES ");

        for(int i =0; i < answers.size(); i++)
        {
            Answer answer = answers.get(i);
            sb.append(" (");
            sb.append("null,");
            sb.append(answer.getQuestionId() + ",");
            sb.append("'" + answer.getAnswerType() + "',");
            sb.append("'" + answer.getAnswerText() + "',");
            sb.append(answer.getAnswerCorrectFlag() + ")");

            if(i!=answers.size()-1)
                sb.append(",");
        }
        sb.append(";");
        DatabaseTasks.ExecuteUpdate(sb.toString());
    }

    public static int InsertAttempt(QuizAttempt attempt)
    {
        int attemptId = 0;
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO QuizHistory VALUES(");
            sb.append("null,");
            sb.append(attempt.getQuizId() + ",");
            sb.append(attempt.getUserId() + ",");
            sb.append(attempt.getAttemptScore() + ",");
            sb.append(attempt.getAttemptPossible() + ",");
            sb.append(attempt.getElapsedTime() + ",");
            sb.append("'" + formatter.format(new Date()) + "');");
            DatabaseTasks.ExecuteUpdate(sb.toString());

            String query = String.format("Select * from QuizHistory WHERE %1$s = %2$s order by AttemptId desc LIMIT 1;", "UserId", attempt.getUserId());
            ResultSet rs = DatabaseTasks.GetResultSet(query);
            rs.next(); // exactly one result so allowed
            attemptId = rs.getInt(1);

            db.UserPersistence.InsertUserActivity(attempt.getUserId(), "QuizTaken", attempt.getQuizId());

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return attemptId;
    }

    public static void InsertQuizStats(int quizId)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO QuizStats VALUES(");
        sb.append(quizId + ",");
        sb.append("0,0,0,0);");
        DatabaseTasks.ExecuteUpdate(sb.toString());
    }

    public static void UpdateQuizStats(QuizAttempt attempt)
    {
        boolean isUniqueUser = IsUniqueAttempt(attempt.getUserId(), attempt.getQuizId());
        int userAttempt = isUniqueUser ? 1 : 0;

        StringBuilder sb = new StringBuilder();
        sb.append("Update QuizStats SET ");
        sb.append("QuizAttempts = QuizAttempts + 1, ");
        sb.append("SumActualScore = SumActualScore + " + attempt.getAttemptScore() + ", ");
        sb.append("SumPossibleScore = SumPossibleScore + " + attempt.getAttemptPossible() + ", ");
        sb.append("UserAttempts = UserAttempts + " + userAttempt);
        sb.append(" WHERE QuizId = " + attempt.getQuizId() + ";");

        DatabaseTasks.ExecuteUpdate(sb.toString());

        db.UserPersistence.InsertUserActivity(attempt.getUserId(), "QuizTaken", attempt.getQuizId());

    }

    public static boolean IsUniqueAttempt(int userId, int quizId)
    {
        String query = String.format("Select * from %1$s WHERE %2$s = %3$s and %4$s = %5$s;","QuizHistory", "UserId", Integer.toString(userId), "QuizId", Integer.toString(quizId));
        boolean doesRecordExist = DatabaseTasks.CheckIfMultipleRecordsExists(query);
        return !doesRecordExist;
    }

    public static void DeleteQuiz(int quizId)
    {
        String query = String.format("Delete from QuizSummary WHERE QuizId = %1$s;", quizId);
        DatabaseTasks.ExecuteUpdate(query);
    }

    public static void DeleteQuestions(int quizId)
    {
        String query = String.format("Delete from QuizQuestions WHERE QuizId = %1$s;", quizId);
        DatabaseTasks.ExecuteUpdate(query);
    }

    public static void DeleteAnswers(int questionId)
    {
        String query = String.format("Delete from QuizAnswers WHERE QuestionId= %1$s;", questionId);
        DatabaseTasks.ExecuteUpdate(query);
    }

    public static void DeleteAttempt(int quizId)
    {
        String query = String.format("Delete from QuizHistory WHERE AttemptId = %1$s;", quizId);
        DatabaseTasks.ExecuteUpdate(query);
    }

    public static void DeleteQuizStats(int quizId)
    {
        String query = String.format("Delete from QuizStats WHERE QuizId = %1$s;", quizId);
        DatabaseTasks.ExecuteUpdate(query);
    }

    public static QuizStats GetQuizStats(int quizId)
    {
        QuizStats qStats = null;

        try {
            ResultSet rs = DatabaseTasks.GetResultSetWithParameter("QuizStats", "QuizId", "" + quizId + "");

            if(rs.next())
            {
                int quizAttempts = Integer.parseInt(rs.getString("QuizAttempts"));
                int totalScore = Integer.parseInt(rs.getString("SumActualScore"));
                int totalPossible = Integer.parseInt(rs.getString("SumPossibleScore"));
                int userAttempts = Integer.parseInt(rs.getString("UserAttempts"));

                qStats = new QuizStats(quizId, quizAttempts, totalScore, totalPossible, userAttempts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return qStats;
    }

    public static double getQuizHighScore(String username, int quizId) {
        double score = 0.0;

        try {
            ResultSet rs = DatabaseTasks.GetResultSet(
                    String.format("SELECT MAX(SumActualScore) AS score FROM QuizStats WHERE QuizId = %1$s;", quizId));

            if(rs.next()) {
                score = rs.getDouble("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return score;
    }

    public static QuizSummary GetQuizSummary(int quizId)
    {
        QuizSummary qSummary = null;

        try {
            ResultSet rs = DatabaseTasks.GetResultSetWithParameter("QuizSummary", "QuizId", "" + quizId + "");

            if(rs.next())
            {
                String quizName = rs.getString("QuizName");
                String quizDescription = rs.getString("QuizDescription");
                int creatorId = Integer.parseInt(rs.getString("CreatorId"));
                String createDate = rs.getString("CreateDate");

                qSummary = new QuizSummary(quizName, quizDescription, creatorId, createDate, quizId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return qSummary;
    }

    public static List<QuizSummary> GetQuizSummaries()
    {
        List<QuizSummary> summaries = new ArrayList<>();

        try {
            ResultSet rs = DatabaseTasks.GetResultSet("*", "QuizSummary");

            while(rs.next())
            {
                int quizId = Integer.parseInt(rs.getString("QuizId"));
                String quizName = rs.getString("QuizName");
                String quizDescription = rs.getString("QuizDescription");
                int creatorId = Integer.parseInt(rs.getString("CreatorId"));
                String createDate = rs.getString("CreateDate");

                summaries.add(new QuizSummary(quizName, quizDescription, creatorId, createDate, quizId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summaries;
    }

    public static int GetNumberOfCreatedQuizzes() {
        int count = 0;

        try {
            ResultSet rs = DatabaseTasks.GetResultSet("*", "QuizSummary");

            while(rs.next()) {
                count++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static List<Activity> GetCreatedQuizzesActivity()
    {
        List<Activity> quizList = new ArrayList<>();

        try {
            ResultSet rs = DatabaseTasks.GetResultSet("*", "UserActivity");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            while(rs.next())
            {
                if(rs.getString("ActivityType").equals("QuizCreated")) {
                    Date date = null;
                    try {
                        date = formatter.parse(rs.getString("ActivityDate"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String type = rs.getString("ActivityType");
                    int linkId = Integer.parseInt(rs.getString("ActivityLinkId"));
                    int userId = Integer.parseInt(rs.getString("UserId"));
                    Activity activity = new Activity(userId, type, formatter.format(date), linkId);
                    quizList.add(activity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizList;
    }

    public static List<Activity> GetTakenQuizzesActivity()
    {
        List<Activity> quizList = new ArrayList<>();

        try
        {
            ResultSet rs = DatabaseTasks.GetResultSet("*", "UserActivity");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");

            while (rs.next())
            {
                if (rs.getString("ActivityType").equals("QuizTaken"))
                {
                    Date date = null;
                    try
                    {
                        date = formatter.parse(rs.getString("ActivityDate"));
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    String type = rs.getString("ActivityType");
                    int linkId = Integer.parseInt(rs.getString("ActivityLinkId"));
                    int userId = Integer.parseInt(rs.getString("UserId"));
                    Activity activity = new Activity(userId, type, formatter.format(date), linkId);
                    quizList.add(activity);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return quizList;
    }

    public static List<Question> GetQuestions(int quizId)
    {
        List<Question> foundQuestions = new ArrayList<>();

        int questionId = 0;
        int quizzId = 0;
        String questionType = "notdefined";
        String questionText = "notdefined";

        ResultSet rs;

        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizQuestions where QuizId = %1$s;", quizId);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                questionId = Integer.parseInt(rs.getString("QuestionId"));
                quizzId = Integer.parseInt(rs.getString("QuizId"));
                questionType = rs.getString("QuestionType");
                questionText =  rs.getString("QuestionText");
                foundQuestions.add(new Question(questionId, quizzId, questionType, questionText));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return foundQuestions;
    }

    public static List<Answer> GetAnswers(int questionId)
    {
        List<Answer> foundAnswers = new ArrayList<>();

        int answerId = 0;
        String answerType = "notdefined";
        String answerText = "notdefined";
        boolean answerCorrectFlag = false;

        ResultSet rs;

        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizAnswers where QuestionId = %1$s;", questionId);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                answerId = Integer.parseInt(rs.getString("AnswerId"));
                answerType = rs.getString("AnswerType");
                answerText =  rs.getString("AnswerText");
                String answerCorrectFlagValue = rs.getString("AnswerCorrectFlag");
                answerCorrectFlag = answerCorrectFlagValue.equals("1");
                foundAnswers.add(new Answer(answerId,questionId,answerType,answerText,answerCorrectFlag));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return foundAnswers;
    }

    public static String GetAnswer(int questionId)
    {
        String correctAnswer = "Answer Not Found";

        ResultSet rs;
        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizAnswers where QuestionId = %1$s and AnswerCorrectFlag = true;", questionId);
            rs = stmt.executeQuery(query);
            if(rs.next())
                correctAnswer = rs.getString("AnswerText");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return correctAnswer;
    }
    public static QuizAttempt GetQuizAttempt(int quizId) {
        QuizAttempt quizAttempt = null;
        // int attemptId = 0;
        int userId = 0;
        int attemptScore = 0;
        int attemptPossible = 0;
        long elapsedTime = 0;
        String dateCreated = "";
        SimpleDateFormat elapsedFormat = new SimpleDateFormat("HH:mm:ss");
        ResultSet rs;
        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizHistory where QuizId = %1$s;", quizId);
            rs = stmt.executeQuery(query);
            if(rs.next())
                //QuizAttempt(int quizId, int userId, int attemptScore, int attemptPossible, long elapsedTime, String dateCreated)
                //attemptId = Integer.parseInt(rs.getString("AttemptId"));
                userId = Integer.parseInt(rs.getString("UserId"));
            attemptScore = Integer.parseInt(rs.getString("AttemptScore"));
            attemptPossible = Integer.parseInt(rs.getString("AttemptPossible"));
            Date d = null;
            try {
                d = elapsedFormat.parse(rs.getString("ElapsedTime"));
            } catch (ParseException  pe) {
                pe.printStackTrace();
            }
            elapsedTime = d.getTime();
            dateCreated = rs.getString("AttemptDate");
            quizAttempt = new QuizAttempt(quizId, userId, attemptScore, attemptPossible, elapsedTime, dateCreated);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return quizAttempt;
    }

    public static List<QuizAttempt> GetAllQuizAttempts(int quizId) {
        List<QuizAttempt> foundAttempts = new ArrayList<QuizAttempt>();
        ResultSet rs;
        int userId = 0;
        int attemptScore = 0;
        int attemptPossible = 0;
        long elapsedTime = 0;
        SimpleDateFormat elapsedFormat = new SimpleDateFormat("HH:mm:ss");
        String dateCreated = "";
        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizHistory where QuizId = %1$s;", quizId);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                userId = Integer.parseInt(rs.getString("UserId"));
                attemptScore = Integer.parseInt(rs.getString("AttemptScore"));
                attemptPossible = Integer.parseInt(rs.getString("AttemptPossible"));
                Date d = null;
                try {
                    d = elapsedFormat.parse(rs.getString("ElapsedTime"));
                } catch (ParseException  pe) {
                    pe.printStackTrace();
                }
                elapsedTime = d.getTime();
                dateCreated = rs.getString("AttemptDate");
                QuizAttempt currentAttempt = new QuizAttempt(quizId, userId, attemptScore, attemptPossible, elapsedTime, dateCreated);
                foundAttempts.add(currentAttempt);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return foundAttempts;
    }

    public static List<QuizAttempt> GetAllPersonalAttempts(int quizId, int userId) {
        List<QuizAttempt> foundAttempts = new ArrayList<QuizAttempt>();
        ResultSet rs;
        int attemptScore = 0;
        int attemptPossible = 0;
        long elapsedTime = 0;
        SimpleDateFormat elapsedFormat = new SimpleDateFormat("HH:mm:ss");
        String dateCreated = "";
        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizHistory where QuizId = %1$s and UserId = %2$s;", quizId, userId);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                userId = Integer.parseInt(rs.getString("UserId"));
                attemptScore = Integer.parseInt(rs.getString("AttemptScore"));
                attemptPossible = Integer.parseInt(rs.getString("AttemptPossible"));
                Date d = null;
                try {
                    d = elapsedFormat.parse(rs.getString("ElapsedTime"));
                } catch (ParseException  pe) {
                    pe.printStackTrace();
                }
                elapsedTime = d.getTime();
                dateCreated = rs.getString("AttemptDate");
                QuizAttempt currentAttempt = new QuizAttempt(quizId, userId, attemptScore, attemptPossible, elapsedTime, dateCreated);
                foundAttempts.add(currentAttempt);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return foundAttempts;
    }

    public static List<QuizAttempt> GetTopAttemptsFromTime(int quizId) {
        List<QuizAttempt> topAttempts = new ArrayList<QuizAttempt>();
        ResultSet rs;
        int userId = 0;
        int attemptScore = 0;
        int attemptPossible = 0;
        long elapsedTime = 0;
        SimpleDateFormat elapsedFormat = new SimpleDateFormat("HH:mm:ss");
        String dateCreated = "";
        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizHistory where QuizId = %1$s and AttemptDate > DATE_SUB(NOW(), INTERVAL 24 HOUR) Order By AttemptScore Desc;", quizId);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                userId = Integer.parseInt(rs.getString("UserId"));
                attemptScore = Integer.parseInt(rs.getString("AttemptScore"));
                attemptPossible = Integer.parseInt(rs.getString("AttemptPossible"));
                Date d = null;
                try {
                    d = elapsedFormat.parse(rs.getString("ElapsedTime"));
                } catch (ParseException  pe) {
                    pe.printStackTrace();
                }
                elapsedTime = d.getTime();
                dateCreated = rs.getString("AttemptDate");
                QuizAttempt currentAttempt = new QuizAttempt(quizId, userId, attemptScore, attemptPossible, elapsedTime, dateCreated);
                topAttempts.add(currentAttempt);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return topAttempts;
    }

    public static List<QuizAttempt> GetMostRecentAttempts(int quizId) {
        List<QuizAttempt> recentAttempts = new ArrayList<QuizAttempt>();
        ResultSet rs;
        int userId = 0;
        int attemptScore = 0;
        int attemptPossible = 0;
        long elapsedTime = 0;
        SimpleDateFormat elapsedFormat = new SimpleDateFormat("HH:mm:ss");
        String dateCreated = "";
        try
        {
            Statement stmt = db.DBConnection.getStatement();

            String query = String.format("Select * from QuizHistory where QuizId = %1$s Order By AttemptScore Desc;", quizId);
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                userId = Integer.parseInt(rs.getString("UserId"));
                attemptScore = Integer.parseInt(rs.getString("AttemptScore"));
                attemptPossible = Integer.parseInt(rs.getString("AttemptPossible"));
                Date d = null;
                try {
                    d = elapsedFormat.parse(rs.getString("ElapsedTime"));
                } catch (ParseException  pe) {
                    pe.printStackTrace();
                }
                elapsedTime = d.getTime();
                dateCreated = rs.getString("AttemptDate");
                QuizAttempt currentAttempt = new QuizAttempt(quizId, userId, attemptScore, attemptPossible, elapsedTime, dateCreated);
                recentAttempts.add(currentAttempt);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return recentAttempts;
    }

}
