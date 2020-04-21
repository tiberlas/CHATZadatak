package agents;

import javax.ejb.Local;

import model.MessagePOJO;

@Local
public interface HostAgentLocal extends Agent {

	void sendPublicMessage(MessagePOJO m);
	void sendPrivateMessage(MessagePOJO m);
}
