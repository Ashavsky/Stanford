package user;


import java.util.Date;

public class Message
{
	private int messageId;
	private int sender;
	private int recipient;
	private String type;
	private String content; //note - messagetext, challenge - challengenote, friend - nothing
	private String messageDate;

	//basic - used for message creation (the rest are pulled down from DB)
	public Message(int recipient, int sender, String type, String content)
	{
		this.recipient = recipient;
		this.sender = sender;
		this.type = type;
		this.content = content;
	}
	//note
	public Message(int messageId, int recipient, int sender, String type, String content, String messageDate)
	{
		this.messageId = messageId;
		this.recipient = recipient;
		this.sender = sender;
		this.type = type;
		this.content = content;
		this.messageDate = messageDate;
	}
	//challenge
	private String challengerScore;
	private int challengerQuizId;
	public Message(int messageId, int recipient, int sender, String type, String challengerNote, String messageDate, String challengerScore, int challengerQuizId)
	{
		this.messageId = messageId;
		this.recipient = recipient;
		this.sender = sender;
		this.type = type;
		this.content = challengerNote;
		this.messageDate = messageDate;
		this.challengerScore = challengerScore;
		this.challengerQuizId = challengerQuizId;
	}
	//friend
	private boolean	friendAccepted;
	public Message(int messageId, int recipient, int sender, String type, String requestStatus, String messageDate, boolean friendAccepted)
	{
		this.messageId = messageId;
		this.recipient = recipient;
		this.sender = sender;
		this.type = type;
		this.content = requestStatus;
		this.messageDate = messageDate;
		this.friendAccepted = friendAccepted;
	}

	public int getMessageId() { return messageId; }
	public void setMessageId(int newId) {messageId = newId; }
	public int getSender() {
		return sender;
	}
	public int getRecipient() {
		return recipient;
	}
	public String getContent() {return content;}
	public String getType() {
		return type;
	}
	public boolean getFriendStatus() { return friendAccepted; }
	public String getChallengerScore() {
		return challengerScore;
	}
	public int getChallengerQuizId() {
		return challengerQuizId;
	}


}