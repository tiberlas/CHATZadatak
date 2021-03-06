package dataBaseService.activeAgents;

import javax.ejb.Local;

import agents.Agent;

@Local
public interface ActiveAgentsLocal {

	Agent getRunningAgent(String username);
	String[] getRunningAgentsNames();
	void addRunningAgent(String username, Agent agent);
	void removeAgent(String username);
	boolean checkIfAgentIsRunning(String username);
	int numberOfRunningAgents();
	void cleanUp();

}