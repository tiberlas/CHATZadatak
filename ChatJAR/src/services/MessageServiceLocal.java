package services;

import javax.ejb.Local;

import model.MessagePOJO;

@Local
public interface MessageServiceLocal {

	void sentPublicMessage(MessagePOJO message);
	void sentPrivateMessage(MessagePOJO message);
	MessagePOJO[] getAllMessages(String user);
	void recivedPublicMessageFromHost(MessagePOJO message);
}
