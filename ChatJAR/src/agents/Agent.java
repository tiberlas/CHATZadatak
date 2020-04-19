package agents;

import javax.jms.Message;

public interface Agent {

	void handleMessage(Message message);
	String getAgentId(); //user name or host alias
}
