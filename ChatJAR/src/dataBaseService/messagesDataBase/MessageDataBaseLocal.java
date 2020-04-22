package dataBaseService.messagesDataBase;

import java.util.List;

import javax.ejb.Local;

import model.MessagePOJO;

@Local
public interface MessageDataBaseLocal {

	void addMessage(MessagePOJO newMessage);
	List<MessagePOJO> getAllMessagesFromUser(String senderName);
	List<MessagePOJO> getAllMessagesForUser(String reciverName);

}
