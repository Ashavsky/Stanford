USE c_cs108_ashavsky;
SET @@auto_increment_increment=1;

INSERT INTO UserDetail VALUES
  (null,'Alex','SomeHashValueHere','SomeSaltValueHere', true, '20130523', 'email@somewhere.com'),
  (null,'Connie','SomeHashValueHere','SomeSaltValueHere', true, '20130523', 'email@somewhere.com'),
  (null,'Scott','SomeHashValueHere','SomeSaltValueHere', false, '20130523', 'email@somewhere.com'),
  (null,'Larry','SomeHashValueHere','SomeSaltValueHere', false, '20130523', 'email@somewhere.com'),
  (null,'John','SomeHashValueHere','SomeSaltValueHere', false, '20130523', 'email@somewhere.com')
;

INSERT INTO UserFriends VALUES
  (1, 2, '20130102'),Sc
  (1, 3, '20130102'),
  (1, 4, '20130102'),
  (1, 5, '20130102'),  
  (2, 1, '20130102'),
  (3, 1, '20130102'),
  (4, 1, '20130102'),
  (5, 1, '20130102')
;
SET @@auto_increment_increment=1;
INSERT INTO UserSocial VALUES 
  (null, 1, 2, 'note', '20120126'),
  (null, 1, 3, 'note', '20120126'),
  (null, 4, 5, 'note', '20120126'),
  (null, 1, 3, 'challenge', '20120126'),
  (null, 1, 2, 'challenge', '20120126'),
  (null, 2, 3, 'friend', '20120126'),
  (null, 4, 5, 'friend', '20120126')
;
SET @@auto_increment_increment=1;
INSERT INTO UserNotes VALUES
  (1, 'Note 1 with a lot of extra text to make sure it can store a lot.'),
  (2, 'Note 2 with a lot of extra text to make sure it can store a lot.'),
  (3, 'Note 3 with a lot of extra text to make sure it can store a lot.')
;

INSERT INTO UserChallenges VALUES
  (4, 'Youre being challenged!', '5 / 10', 1),
  (5, 'Youre being challenged!', '19 / 20', 2)
;

INSERT INTO UserFriendRequests VALUES
  (6, 'Sent', false),
  (7, 'Rejected', false)
;

INSERT INTO UserAchievements VALUES
  (null, 1, 'QuizTaker', 'You are the take of quizzes', '20130523', 'sometooltip.jpg'),
  (null, 4, 'LoneWolf', 'A lone wolf rides alone', '20130129', 'sometooltip.jpg'),
  (null, 3, 'MasterQuizTaker', 'Master of All', '20130513', 'sometooltip.jpg'),
  (null, 2, 'TestWriter', 'you wrote a test!', '20130423', 'sometooltip.jpg'),
  (null, 5, 'KingOfTheQuizzes', 'Not the master, but the king is cool too', '20120523', 'sometooltip.jpg')
;


INSERT INTO UserActivity VALUES
  (null, 1, 'Achievement', 1, '20130523'),
  (null, 4, 'Achievement', 2, '20130523'),
  (null, 3, 'Achievement', 3, '20130523'),
  (null, 2, 'Achievement', 4, '20130523'),
  (null, 5, 'Achievement', 5, '20130523'),
  (null, 1, 'Achievement', 1, '20130523'),
  (null, 1, 'Achievement', 1, '20130523'),
  (null, 1, 'Quiz', 1, '20130221'),
  (null, 2, 'Quiz', 2, '20121223'),
  (null, 1, 'Quiz', 3, '20130512')
;