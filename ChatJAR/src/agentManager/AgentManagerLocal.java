package agentManager;

import javax.ejb.Local;

@Local
public interface AgentManagerLocal {

	void startAgent(String user);
	void stopAgent(String name);
	String[] getAgents();
	String getHostName();
}
