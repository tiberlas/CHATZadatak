package messageManager;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import agents.Agent;
import dataBaseService.activeAgents.ActiveAgentsLocal;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/publicTopic") })
public class AgentsMDB implements MessageListener{

	@EJB
	private ActiveAgentsLocal agents;
	
	public AgentsMDB() {
		super();
	}
	
	@Override
	public void onMessage(Message message) {
		String receiver;
		try {
			receiver = (String) message.getObjectProperty("receiver");
			Agent agent = (Agent) agents.getRunningAgent(receiver);
			agent.handleMessage(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
