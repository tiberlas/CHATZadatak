package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import agents.HostAgentLocal;
import dataBaseService.existingUsers.UserDataBaseLocal;
import dataBaseService.messagesDataBase.MessageDataBaseLocal;
import model.MessagePOJO;
import model.UserPOJO;

@Stateless
public class MessageService implements MessageServiceLocal {

	@EJB
	private MessageDataBaseLocal messages;
	
	@EJB
	private UserDataBaseLocal users;
	
	@EJB
	private HostAgentLocal host;
	
	@Override
	public void sentPublicMessage(MessagePOJO message) {
		setTime(message);
		
		//send to host, after host will send to all agents
		message.setReciver("PUBLIC");
		host.sendPublicMessage(message);
		messages.addMessage(message);
		
		//saves in DB
		for(UserPOJO user: users.getAllUsers()) {
			if(!user.getUsername().equals(message.getSender())) {
				message.setReciver(user.getUsername());
				messages.addMessage(message);
			}
		}
		
	}

	@Override
	public void sentPrivateMessage(MessagePOJO message) {
		setTime(message);
		messages.addMessage(message);
		
		host.sendPrivateMessage(message);
	}

	@Override
	public MessagePOJO[] getAllMessages(String user) {
		List<MessagePOJO> allMessages = new ArrayList<MessagePOJO>();
		allMessages.addAll(messages.getAllMessagesForUser(user));
		allMessages.addAll(messages.getAllMessagesFromUser(user));
		
		return allMessages.toArray(new MessagePOJO[allMessages.size()]);
	}
	
	private void setTime(MessagePOJO message) {
		message.setCreationDate(new Date());
	}


}
