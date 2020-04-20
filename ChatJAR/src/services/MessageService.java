package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dataBaseService.messagesDataBase.MessageDataBaseLocal;
import model.MessagePOJO;

@Stateless
public class MessageService implements MessageServiceLocal {

	@EJB
	private MessageDataBaseLocal messages;
	
	@Override
	public void sentPublicMessage(MessagePOJO message) {
		messages.addMessage(message);
		//TODO: sent JMS to HOST
	}

	@Override
	public void sentPrivateMessage(MessagePOJO message) {
		messages.addMessage(message);
		//TODO: sent JMS to HOST
	}

	@Override
	public MessagePOJO[] getAllMessages(String user) {
		List<MessagePOJO> allMessages = new ArrayList<MessagePOJO>();
		allMessages.addAll(messages.getAllMessagesForUser(user));
		allMessages.addAll(messages.getAllMessagesFromUser(user));
		
		return allMessages.toArray(new MessagePOJO[allMessages.size()]);
	}

}
