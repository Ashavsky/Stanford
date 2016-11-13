package quiz;

import db.DatabaseTasks;
import db.QuizPersistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 3/9/2016.
 */
public class QuizRepository
{
    public static Quiz GetQuiz(int quizId)
    {
        QuizSummary summary = GetQuizSummary(quizId);
        QuizStats stats = GetQuizStats(quizId);
        List<Question> questions = GetQuestions(quizId);

        return new Quiz(quizId, summary, stats, questions);
    }

    public static int AddQuizHeader(QuizSummary qz) //this is not complete, needs to add more places + take in quiz
    {
        int quizId = AddQuizSummary(qz);
        AddQuizStats(quizId);
        return quizId;
    }

    public static void AddQuizContent(List<Question> questions, int quizId)
    {
        for(Question question : questions)
        {
            question.setQuizId(quizId);
            int questionId = AddQuestion(question);
            for(Answer answer : question.getAnswers())
                answer.setQuestionId(questionId);
            AddAnswers(question.getAnswers());
        }
    }

    public static int AddQuestion(Question question)
    {
        return QuizPersistence.InsertQuestion(question);
    }

    public static void AddQuestions(List<Question> questions)
    {
        QuizPersistence.InsertQuestions(questions);
    }

    public static void AddAnswers(List<Answer> answers)
    {
        QuizPersistence.InsertAnswers(answers);
    }

    public static int AddQuizSummary(QuizSummary qz)
    {
        return QuizPersistence.InsertQuizSummary(qz);
    }

    public static void AddQuizStats(int quizId)
    {
        QuizPersistence.InsertQuizStats(quizId);
    }

    public static int AddAttempt(QuizAttempt attempt)
    {
        return QuizPersistence.InsertAttempt(attempt);
        //needs to update activity + quizhistory
    }

    public static void RemoveQuiz(int quizId)
    {
        QuizPersistence.DeleteQuiz(quizId);
        QuizPersistence.DeleteQuizStats(quizId);
    }

    public static void RemoveQuestions(int quizId)
    {
        QuizPersistence.DeleteQuestions(quizId);
    }

    public static void RemoveAnswers(int questionId)
    {
        QuizPersistence.DeleteAnswers(questionId);
    }

    public static void RemoveAttempt(int attemptId)
    {
        QuizPersistence.DeleteAttempt(attemptId);
    }

    public static boolean QuizExists(int quizId)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParameterInt("QuizSummary", "QuizId", Integer.toString(quizId));
    }

    public static boolean QuestionsExist(int quizId)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParameterInt("QuizSummary", "QuizId", Integer.toString(quizId));
    }

    public static boolean AttemptExists(int attemptId)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParameterInt("QuizHistory", "AttemptId", Integer.toString(attemptId));
    }

    public static boolean QuizStatsExists(int quizId)
    {
        return DatabaseTasks.CheckIfRecordExistsWithParameterInt("QuizStats", "QuizId", Integer.toString(quizId));
    }

    public static void UpdateQuizStats(QuizAttempt attempt)
    {
        QuizPersistence.UpdateQuizStats(attempt);
    }

    public static QuizStats GetQuizStats(int quizId)
    {
        QuizStats qStats = null;

        if(QuizExists(quizId))
            qStats = db.QuizPersistence.GetQuizStats(quizId);

        return qStats;
    }

    public static QuizSummary GetQuizSummary(int quizId)
    {
        QuizSummary qSummary = null;

        if(QuizExists(quizId))
            qSummary = db.QuizPersistence.GetQuizSummary(quizId);

        return qSummary;
    }

    public static List<Question> GetQuestions(int quizId)
    {
        List<Question> questions = db.QuizPersistence.GetQuestions(quizId);
        return questions;
    }

    public static List<Answer> GetAnswers(int questionId)
    {
        List<Answer> answers = db.QuizPersistence.GetAnswers(questionId);
        return answers;
    }

    public static boolean CheckAnswer(int questionId, String answerText)
    {
        String dbAnswerText = db.QuizPersistence.GetAnswer(questionId);
        boolean isAnswerCorrect = answerText.equals(dbAnswerText);
        return isAnswerCorrect;
    }

    public static List<Integer> CheckAnswerWithPoints(int questionId, List<String> answers) //returns 2 items in list, score recieved and score possible
    {
        int possiblePoints = 1;
        int correctPoints = 0;
        List<Answer> dbAnswers = db.QuizPersistence.GetAnswers(questionId);
        List<String> dbCorrectAnswers = new ArrayList<>();

        for(Answer answer : dbAnswers)
        {
            if(answer.getAnswerCorrectFlag())
            {
                dbCorrectAnswers.add(answer.getAnswerText());
//                possiblePoints++;
            }
        }

        List<String> uniqueAnswerGuesses = new ArrayList<>(); //Need this so they can't get double points for same answer
        for(String guessAnswer : answers)
        {
            if(!uniqueAnswerGuesses.contains(guessAnswer))
                uniqueAnswerGuesses.add(guessAnswer);
        }


        for(String correctAnswer : dbCorrectAnswers)
        {
            for(String attemptAnswer : uniqueAnswerGuesses)
            {
                if(attemptAnswer.toLowerCase().equals(correctAnswer))
                    correctPoints++;
            }
        }

        List<Integer> scoreAchievedScorePossbileList = new ArrayList<>();
        scoreAchievedScorePossbileList.add(correctPoints);
        scoreAchievedScorePossbileList.add(possiblePoints);

        return scoreAchievedScorePossbileList;
    }

    public static List<Answer> GetAllAnswersForQuiz(int quizId) //Note sure this is needed, only if DB perf still bad
    {
//        List<Answer> answers = db.QuizPersistence.GetAllAnswersForQuiz(quizId);
        return null;
    }

    public static List<QuizSummary> GetAllQuizSummaries()
    {
        List<QuizSummary> summaries = QuizPersistence.GetQuizSummaries();
        return summaries;
    }
}
