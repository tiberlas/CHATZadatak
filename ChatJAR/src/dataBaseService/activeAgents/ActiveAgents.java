package dataBaseService.activeAgents;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import agents.Agent;

@Singleton
public class ActiveAgents implements ActiveAgentsLocal {

	/**
	 * BD for agents that are created on THIS HOST; has agents that are user's representation; and has exactly ONE host agent;
	 * 
	 * */
	
	private Map<String, Agent> runningAgents;
	
	public ActiveAgents() {
		runningAgents = new HashMap<String, Agent>();
	}
	
	@Override
	public Agent getRunningAgent(String username) {
		return runningAgents.get(username);
	}
	
	@Override
	public String[] getRunningAgentsNames() {
		return (String[]) runningAgents.keySet().toArray(new String[runningAgents.size()]);
	}

	@Override
	public void addRunningAgent(String username, Agent agent) {
		runningAgents.put(username, agent);
	}
	
	@Override
	public void removeAgent(String username) {
		runningAgents.remove(username);
	}
	
	@Override
	public boolean checkIfAgentIsRunning(String username) {
		return runningAgents.containsKey(username);
	}

	@Override
	public int numberOfRunningAgents() {
		return runningAgents.size();
	}
	
	@PreDestroy
	@Override
	public void cleanUp() {
		runningAgents.clear();
	}
}
