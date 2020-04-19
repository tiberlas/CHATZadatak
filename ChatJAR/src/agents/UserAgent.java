package agents;

import javax.ejb.Stateful;
import javax.jms.Message;

@Stateful
public class UserAgent implements Agent{

	private String agentName;
	
	public UserAgent(String username) {
		super();
		agentName = username;
	}
	
	@Override
	public void handleMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAgentId() {
		return agentName;
	}

}
