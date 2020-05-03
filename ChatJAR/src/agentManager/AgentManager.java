package agentManager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import agents.HostAgentLocal;
import agents.UserAgentLocal;
import dataBaseService.activeAgents.ActiveAgentsLocal;
import model.ActiveUserPOJO;
import ws.MessagesWS;

@Stateless
@LocalBean
public class AgentManager implements AgentManagerLocal{

	@EJB
	private ActiveAgentsLocal agents;
	
	@EJB
	private HostAgentLocal hostAgent;

	@EJB
	private UserAgentLocal agent;
	
	@EJB
	private MessagesWS ws;
	
	@Override
	public boolean startAgent(String user) {
		if(!agents.checkIfAgentIsRunning(user)) {
			agent.startUp(user, hostAgent.getAgentId());
			agents.addRunningAgent(user, agent);
			
			hostAgent.addActiveUser(new ActiveUserPOJO(user, hostAgent.getAgentId()));
			
			System.out.println("Starting agent " + user);
			return true;
		} else return false;
	}

	@Override
	public void stopAgent(String name) {
		System.out.println("Removeing agent " + name);
		agents.removeAgent(name);
		hostAgent.removeActiveUser(name);
		ws.removeAgent(name);
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
