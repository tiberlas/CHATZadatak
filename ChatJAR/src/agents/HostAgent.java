package agents;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.jms.JMSException;
import javax.jms.Message;

import dataBaseService.activeAgents.ActiveAgentsLocal;
import messageManager.MessageManagerForAgentsLocal;
import model.MessagePOJO;

@Singleton
public class HostAgent implements HostAgentLocal {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ActiveAgentsLocal activeAgents;
	
	@EJB
	private MessageManagerForAgentsLocal messageManager;
	
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
		try {
			//TODO: primljenu poruku proslediti agentu ili hostu
			System.out.println("HOST recived a message from " + message.getStringProperty("sender"));
			System.out.println("content " + message.getStringProperty("subject"));
			System.out.println("-----------------------");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendPrivateMessage(MessagePOJO message) {
		if(activeAgents.checkIfAgentIsRunning(message.getReciver())) {
			messageManager.sendMessage(message);
		} else {
			//TODO: salje se drugom hostu; treba nam spisak hostova i agenata na svakom hostu
		}
	}
	
	@Override
	public void sendPublicMessage(MessagePOJO message) {
		for(String reciver: activeAgents.getRunningAgentsNames()) {
			message.setReciver(reciver);
			messageManager.sendMessage(message);
		}
	}

	@Override
	public String getAgentId() {
		return hostName;
	}

}
