package agents;


import java.util.Date;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.jms.JMSException;
import javax.jms.Message;

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
		
		try {			
			System.out.println("recived a message from " + message.getStringProperty("sender"));
			System.out.println("to " + message.getStringProperty("reciver"));
			System.out.println("header " + message.getStringProperty("header"));
			System.out.println("content " + message.getStringProperty("subject"));
			System.out.println(message.getStringProperty("creationDate"));
			System.out.println("--------------------------------");
			
			MessagePOJO msg = new MessagePOJO(
					message.getStringProperty("reciver"),
					message.getStringProperty("sender"),
					message.getStringProperty("header"),
					message.getStringProperty("subject"),
					new Date()
					);
			
			//poslati web socketu poruku
			ws.echoTextMessage("HELLO THERE"); //NULL POINTER EXP

		} catch (JMSException e) {
			System.out.println("JMS EXCEPTION");
			//e.printStackTrace();
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
