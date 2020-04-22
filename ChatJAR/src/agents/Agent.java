package agents;

import java.io.Serializable;

import javax.jms.Message;

public interface Agent extends Serializable{

	void handleMessage(Message message);
	String getAgentId(); //user name or host alias
}
