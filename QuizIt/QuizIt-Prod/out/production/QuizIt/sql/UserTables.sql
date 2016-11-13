USE c_cs108_ashavsky;
SET @@auto_increment_increment=1;
 
CREATE TABLE IF NOT EXISTS UserDetail 
(
	 UserId int AUTO_INCREMENT,
	 UserName varchar(100),
	 UserPassword varchar(100),
	 UserSalt varchar(100),
	 AdminFlag boolean,
	 UserCreateDate datetime,
	 UserEmail varchar(100),
	 PRIMARY KEY (UserId)
 );
 
CREATE TABLE IF NOT EXISTS UserFriends 
(
	 UserId int,
	 FriendId int,
	 FriendAddDate datetime
 );
 
CREATE TABLE IF NOT EXISTS UserSocial 
(
	 MessageId int AUTO_INCREMENT,
	 UserId int,
	 FriendId int,
	 MessageType varchar(20),
	 PRIMARY KEY (MessageId)
 );
 
CREATE TABLE IF NOT EXISTS UserNotes
(
	 MessageId int,
	 MessageText TEXT,
	 PRIMARY KEY (MessageId)
 );
 
CREATE TABLE IF NOT EXISTS UserChallenges 
(
	 MessageId int,
	 ChallengerNote TEXT,
	 ChallengeScore varchar(40),
	 ChallengerQuizId int,
	 PRIMARY KEY (MessageId)
 );
 
CREATE TABLE IF NOT EXISTS UserFriendRequests 
(
	 MessageId int,
	 RequestStatus varchar(40),
	 FriendAccepted boolean,
	 PRIMARY KEY (MessageId)
 );
 
CREATE TABLE IF NOT EXISTS UserAchievements 
(
	 AchievementId int AUTO_INCREMENT,
	 UserId int,
	 AchievementName varchar(20),
	 AchievementDescription varchar(100),
	 AchievementDate datetime,
	 AchievementToolTip varchar(100),
	 PRIMARY KEY (AchievementId)
 );
 
 
CREATE TABLE IF NOT EXISTS UserActivity 
(
	 ActivityId int AUTO_INCREMENT,
	 UserId int,
	 ActivityType varchar(40),
	 ActivityLinkId int,
	 ActivityDate datetime,
	 PRIMARY KEY (ActivityId)
 );