package agents;


import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.jms.Message;

import adapter.MessageJMSToPOJOAdapter;
import model.MessagePOJO;
import ws.MessagesWS;

@Stateful
@LocalBean
public class UserAgent implements UserAgentLocal{

	private static final long serialVersionUID = 1L;
	
	private String agentName;
	private String hostName;
	
	@EJB 
	private MessagesWS ws;
	
	public UserAgent() {
		super();
	}
	
	@Override
	public void startUp(String username, String hostName) {
		agentName = username;
		this.hostName = hostName;
		System.out.println("User agent started: " + agentName + " on host " + hostName);
	}
	
	@Override
	public void handleMessage(Message message) {
		/**
		 * dobije od MessageDrivenBean tj od MessageManagerForAgents dobije message(JMS)
		 * sva polja od JMS su stringovi
		 * */
		MessagePOJO msg = MessageJMSToPOJOAdapter.convertFromJmsToPojo(message);		
			
		//poslati web socketu poruku
		if(msg != null) {	
			ws.sendMessage(msg);
		}
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
