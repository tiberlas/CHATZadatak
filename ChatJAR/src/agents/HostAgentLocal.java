package agents;

import java.util.List;

import javax.ejb.Local;

import model.ActiveUserPOJO;
import model.HostPOJO;
import model.MessagePOJO;

@Local
public interface HostAgentLocal extends Agent {

	void sendPublicMessage(MessagePOJO m);
	void sendPrivateMessage(MessagePOJO m);
	HostPOJO getMaster();
	void addNewHostNode(HostPOJO hostNode);
	void addNewHostNodes(List<HostPOJO> hostNode);
	void removeNewHostNode(String hostName);
	void registerHostNode(HostPOJO newHostNode);
	void addActiveUser(ActiveUserPOJO user);
	void removeActiveUser(String username);
	void sendMessageToAllMyActiveAgents(MessagePOJO message);
}
