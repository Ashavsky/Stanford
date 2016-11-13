package user;

import java.util.*;

public class Messaging {
	private static Map<String, List<String>> messages = new HashMap<String, List<String>>();
	
	public static void addMessage(Message msg) {
		// DB code
		String id = String.valueOf(messages.size());
		messages.put(id, Arrays.asList(msg.getSender(), msg.getRecipient()));
		User.addMessage(msg.getSender(), id);
		User.addMessage(msg.getRecipient(), id);
	}
	
	public static void removeMessage(String id) {
		// DB code
		if (messages.containsKey(id)) {
			User.removeMessage(messages.get(id).get(0), id);
			User.removeMessage(messages.get(id).get(1), id);
			messages.remove(id);
		}
	}
	
	public static Message getMessage(String id) {
		// DB code
		String sender = messages.get(id).get(0);
		String recipient = messages.get(id).get(1);
		String type = "type";
		String content = "content";
		// DB code
		return new Message(sender, recipient, type, content);
	}
}