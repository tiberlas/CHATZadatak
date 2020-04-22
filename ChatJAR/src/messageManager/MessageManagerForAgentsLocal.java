package messageManager;

import javax.ejb.Local;

import model.MessagePOJO;

@Local
public interface MessageManagerForAgentsLocal {

	void sendMessage(MessagePOJO newMessage);

}