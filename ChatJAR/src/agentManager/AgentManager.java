package agentManager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import agents.HostAgentLocal;
import agents.UserAgent;
import dataBaseService.activeAgents.ActiveAgentsLocal;

@Stateless
@LocalBean
public class AgentManager implements AgentManagerLocal{

	@EJB
	private ActiveAgentsLocal agents;
	@EJB
	private HostAgentLocal hostAgent;

	@Override
	public boolean startAgent(String user) {
		if(!agents.checkIfAgentIsRunning(user)) {
			agents.addRunningAgent(user, new UserAgent(user, hostAgent.getAgentId()));
			System.out.println("Starting agent " + user);
			return true;
		} else return false;
	}

	@Override
	public void stopAgent(String name) {
		System.out.println("Removeing agent " + name);
		agents.removeAgent(name);
	}

	@Override
	public String[] getAgents() {
		//returns all user agents; host agent is removed from the list
		String[] allAgents = agents.getRunningAgentsNames();
		String[] userAgents = new String[allAgents.length-1];
		
		int i = 0;
		for(String agentName : allAgents) {
			if(!agentName.equals(hostAgent.getAgentId())) {
				userAgents[i++] = new String(agentName);
			}
		}
		
		return userAgents; 
	}

	@Override
	public String getHostName() {
		return hostAgent.getAgentId();
	}
}
