USE c_cs108_ashavsky;
SET @@auto_increment_increment=1;

CREATE TABLE IF NOT EXISTS QuizSummary 
(
	 QuizId int AUTO_INCREMENT,
	 QuizName varchar(100),
	 QuizDescription varchar(100),
	 CreatorId int,
	 CreateDate datetime,
	 PRIMARY KEY (QuizId)
 );
 
CREATE TABLE IF NOT EXISTS QuizHistory 
(
	 AttemptId int AUTO_INCREMENT,
	 QuizId int,
	 UserId int,
	 AttemptScore int,
	 AttemptPossible int,
	 ElapsedTime time,
	 AttemptDate datetime,
	 PRIMARY KEY (AttemptId)
);

CREATE TABLE IF NOT EXISTS QuizStats 
(
	 QuizId int,
	 QuizAttempts int,
	 SumActualScore int,
	 SumPossibleScore int,
	 UserAttempts int
 );

CREATE TABLE IF NOT EXISTS QuizQuestions 
(
	 QuestionId int AUTO_INCREMENT,
	 QuizId int,
	 QuestionType varchar(20),
	 QuestionText TEXT,
	 PRIMARY KEY (QuestionId)
 );

CREATE TABLE IF NOT EXISTS QuizAnswers 
(
	 AnswerId int AUTO_INCREMENT,
	 QuestionId int,
	 AnswerType varchar(20),
	 AnswerText TEXT,
	 AnswerCorrectFlag boolean,
	 PRIMARY KEY (AnswerId)
 ); 
 
 

 

 