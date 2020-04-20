package agents;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.jms.Message;

import dataBaseService.activeAgents.ActiveAgentsLocal;

@Singleton
public class HostAgent implements HostAgentLocal {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ActiveAgentsLocal activeAgents;
	
	private String hostName;
	
	@PostConstruct
	public void setUp() {
		//TODO: server discovery protocol
		
		hostName = "master";
		activeAgents.addRunningAgent(hostName, this);
		System.out.println("Host agent started: " + hostName);
	}
	
	@Override
	public void handleMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAgentId() {
		return hostName;
	}

}
