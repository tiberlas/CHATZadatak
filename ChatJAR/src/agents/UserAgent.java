package agents;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.jms.JMSException;
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
		try {
			//TODO: poslati web socketu poruku
			System.out.println("recived a message from " + message.getStringProperty("sender"));
			System.out.println("to " + message.getStringProperty("reciver"));
			System.out.println("header " + message.getStringProperty("header"));
			System.out.println("content " + message.getStringProperty("subject"));
			System.out.println(message.getObjectProperty("creationDate"));
			System.out.println("--------------------------------");
		} catch (JMSException e) {
			e.printStackTrace();
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
