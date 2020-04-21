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
		
		addFakeData();
	}
	
	@Override
	public void addMessage(MessagePOJO newMessage) {
		addSentMessage(newMessage);
		addRecivedMessage(newMessage);
	}

	@Override
	public List<MessagePOJO> getAllMessagesFromUser(String senderName) {
		if(sentMessagesMap.containsKey(senderName)) {
			return new ArrayList<MessagePOJO>(sentMessagesMap.get(senderName));
		} else return null;
	}

	@Override
	public List<MessagePOJO> getAllMessagesForUser(String reciverName) {
		if(recivedMessagesMap.containsKey(reciverName)) {
			return new ArrayList<MessagePOJO>(recivedMessagesMap.get(reciverName));
		} else return null;
	}

	private void addSentMessage(MessagePOJO newMessage) {
		String sender = newMessage.getSender();
		
		if(!sentMessagesMap.containsKey(sender)) {
			sentMessagesMap.put(sender, new ArrayList<MessagePOJO>());
		}
		sentMessagesMap.get(sender).add(newMessage);
	} 
	
	private void addRecivedMessage(MessagePOJO newMessage) {
		String reciver = newMessage.getReciver();
		
		if(!recivedMessagesMap.containsKey(reciver)) {
			recivedMessagesMap.put(reciver, new ArrayList<MessagePOJO>());
		}
		recivedMessagesMap.get(reciver).add(newMessage);
	} 
	
	private void addFakeData() {
		int SIZE = 3;
		MessagePOJO[] fakeMessages = new MessagePOJO[SIZE];
		fakeMessages[0] = new MessagePOJO("tibi", "svetlana", "TEST ONE", "If you read this then the system is working propperly.");
		fakeMessages[1] = new MessagePOJO("svetlana", "tibi", "TEST ONE", "Yse, I recived it.");
		fakeMessages[2] = new MessagePOJO("tibi", "svetlana", "TEST ONE", "This is promising");
		
		for(int i=0; i<SIZE; ++i) {
			addMessage(fakeMessages[i]);
		}
		
	}
	
}
