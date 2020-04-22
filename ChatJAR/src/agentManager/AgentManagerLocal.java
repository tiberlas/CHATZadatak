package agentManager;

import javax.ejb.Local;

@Local
public interface AgentManagerLocal {

	boolean startAgent(String user);
	void stopAgent(String name);
	String[] getAgents();
	String getHostName();
}
