USE c_cs108_ashavsky

INSERT INTO QuizSummary VALUES
  (null,'Quiz One','Description Of Quiz One', 3, '19881221'),
  (null,'Quiz Two','Description Of Quiz Two', 4, '20010101'),
  (null,'Quiz Three','Description Of Quiz Three', 5, '20101010')
;

INSERT INTO QuizHistory VALUES
  (null,1, 1, 3, 5, '000200', '20160101'),
  (null,1, 2, 4, 5, '000100', '20160102'),
  (null,2, 1, 1, 5, '000200', '20160303')
;

INSERT INTO QuizStats VALUES
	(1, 4, 40, 60, 2),
	(2, 10, 100, 200, 5),
	(1, 20, 200, 1000, 19)
 ;
 
INSERT INTO QuizQuestions VALUES
	(null, 1, 'multchoice', 'Sample Question 1 for Quiz 1'),
	(null, 1, 'picture', 'Sample Question 2 for Quiz 1'),	
	(null, 2, 'multchoice', 'Sample Question 1 for Quiz 2'),
	(null, 2, 'picture', 'Sample Question 2 for Quiz 2')
 ;
 
INSERT INTO QuizAnswers VALUES
	(null, 1, 'multchoice', 'Sample Answer 1 for Question 1', true),
	(null, 1, 'multchoice', 'Sample Answer 2 for Question 1', false),
	(null, 2, 'picture', 'Sample Answer 3 for Question 2', true),
	(null, 2, 'picture', 'Sample Answer 4 for Question 2', false),
	(null, 3, 'multchoice', 'Sample Answer 5 for Question 3', true),
	(null, 3, 'multchoice', 'Sample Answer 6 for Question 3', false),
	(null, 4, 'picture', 'Sample Answer 6 for Question 4', true),
	(null, 4, 'picture', 'Sample Answer 7 for Question 4', false)
;