Select us.MessageId, UserId, FriendId, MessageType, MessageDate, MessageText as Content from UserSocial us inner join UserNotes un on us.MessageId = un.MessageId where UserId = 1;
Select us.MessageId, UserId, FriendId, MessageType, MessageDate, RequestStatus as Content, FriendAccepted from UserSocial us inner join UserFriendRequests uf on us.MessageId = uf.MessageId where UserId = 2;
Select us.MessageId, UserId, FriendId, MessageType, MessageDate, ChallengerNote as Content, ChallengeScore, ChallengerQuizId from UserSocial us inner join UserChallenges uc on us.MessageId = uc.MessageId where UserId = 1;

Select us.MessageId, UserId, FriendId, MessageType, MessageDate, MessageText, RequestStatus, FriendAccepted, ChallengerNote, ChallengeScore, ChallengerQuizId from UserSocial us left join UserChallenges uc on us.MessageId = uc.MessageId left join UserNotes un on us.MessageId = un.MessageId left join UserFriendRequests uf on us.MessageId = uf.MessageId where UserId = 1;

Select * from UserSocial us inner join UserFriendRequests uf on us.MessageId = uf.MessageId WHERE UserId = 3 and FriendId = 2;