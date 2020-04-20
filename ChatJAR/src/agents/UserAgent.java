package agents;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.jms.Message;

@Stateful
public class UserAgent implements UserAgentLocal{

	private static final long serialVersionUID = 1L;
	
	private String agentName;
	private String hostName;
	
	public UserAgent(String username, String hostName) {
		super();
		agentName = username;
		this.hostName = hostName;
		System.out.println("User agent started: " + agentName + " on host " + hostName);
	}
	
	@Override
	public void handleMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAgentId() {
		return agentName;
	}

	@Override
	public String getHostName() {
		return hostName;
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("Agent " + agentName + " on host " + hostName + " is removed!");
	}
}
