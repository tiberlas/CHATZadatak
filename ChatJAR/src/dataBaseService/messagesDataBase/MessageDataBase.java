package dataBaseService.messagesDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

import model.MessagePOJO;

@Singleton
public class MessageDataBase implements MessageDataBaseLocal {

	/**
	 * represents fake data base for messages;
	 * has two HashMaps for storing messages
	 * one for sent messages; key is the name of the sender
	 * and one for recieving messages; key is the reciever's name
	 * 
	 * */
	
	private Map<String, List<MessagePOJO>> sentMessagesMap;
	private Map<String, List<MessagePOJO>> recivedMessagesMap;
	
	public MessageDataBase() {
		super();
		sentMessagesMap = new HashMap<>();
		recivedMessagesMap = new HashMap<>();
	}
	
	@Override
	public void addMessage(MessagePOJO newMessage) {
		String sender = newMessage.getSender();
		String reciver = newMessage.getReciver();
		
		if(!sentMessagesMap.containsKey(sender)) {
			sentMessagesMap.put(sender, new ArrayList<MessagePOJO>());
		}
		sentMessagesMap.get(sender).add(newMessage);
		
		if(!recivedMessagesMap.containsKey(reciver)) {
			recivedMessagesMap.put(reciver, new ArrayList<MessagePOJO>());
		}
		recivedMessagesMap.get(reciver).add(newMessage);
	}

	@Override
	public List<MessagePOJO> getAllMessagesFromUser(String senderName) {
		return new ArrayList<MessagePOJO>(sentMessagesMap.get(senderName));
	}

	@Override
	public List<MessagePOJO> getAllMessagesForUser(String reciverName) {
		return new ArrayList<MessagePOJO>(recivedMessagesMap.get(reciverName));
	}

}
