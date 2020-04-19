package agentManager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import agents.HostAgent;
import agents.UserAgent;
import dataBaseService.activeAgents.ActiveAgentsLocal;

@Stateless
@LocalBean
public class AgentManager implements AgentManagerLocal{

	@EJB
	private ActiveAgentsLocal agents;
	@EJB
	private HostAgent hostAgent;

	@Override
	public void startAgent(String user) {
		agents.addRunningAgent(user, new UserAgent(user));
		
	}

	@Override
	public void stopAgent(String name) {
		agents.removeAgent(name);
	}

	@Override
	public String[] getAgents() {
		String[] allAgents = agents.getRunningAgentsNames();
		String[] userAgents = new String[allAgents.length-1];
		
		int i = 0;
		for(String agentName : allAgents) {
			if(!agentName.equals(hostAgent.getAgentId())) {
				userAgents[i++] = agentName;
			}
		}
		
		return userAgents; 
	}

	@Override
	public String getHostName() {
		return hostAgent.getAgentId();
	}
}
