package agents;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSException;
import javax.jms.Message;

import dataBaseService.activeAgents.ActiveAgentsLocal;
import messageManager.MessageManagerForAgentsLocal;
import model.MessagePOJO;

@Startup
@Singleton
public class HostAgent implements HostAgentLocal {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ActiveAgentsLocal activeAgents;
	
	@EJB
	private MessageManagerForAgentsLocal messageManager;
	
	@EJB
	private ServerDiscovery serverDiscovery;
	
	private String hostName;
	private String ipAddress;
	
	@PostConstruct
	public void setUp() {
		try {
			Inet4Address ipV4 = (Inet4Address) Inet4Address.getLocalHost();
			ipAddress = ipV4.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			ipAddress = "localhost";
		}
		
		hostName = serverDiscovery.getHostName();
		
		activeAgents.addRunningAgent(hostName, this);
		System.out.println("Host agent started: " + hostName + " ipV4" + ipAddress);
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
			if(!reciver.equals(message.getSender())) {
				message.setReciver(reciver);
				messageManager.sendMessage(message);				
			}
		}
	}

	@Override
	public String getAgentId() {
		return hostName;
	}

}
