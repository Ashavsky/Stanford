package user;


public class Message {
	private String sender;
	private String recipient;
	private String type;
	private String content;
	
	public Message(String sender, String recipient, String type, String content) {
		this.sender = sender;
		this.recipient = recipient;
		this.type = type;
		this.content = content;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getType() {
		return type;
	}
}