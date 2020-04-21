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
		try {
			String reciver = message.getStringProperty("reciver");
			Agent agent = (Agent) agents.getRunningAgent(reciver);
			if(agent != null) {
				agent.handleMessage(message);
			} else {
				System.out.println("FATAL ERROR AGENT IS NOT IN THE LIST");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
