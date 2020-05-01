package messageManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import model.MessagePOJO;

@Stateless
@LocalBean
public class MessageManagerForAgents implements MessageManagerForAgentsLocal {

	public MessageManagerForAgents() {
		super();
	}

	@EJB
	private JMSFactory factory;
	
	private Session session;
	private MessageProducer defaultProducer;

	@PostConstruct
	public void postConstruct() {
		session = factory.getSession();
		defaultProducer = factory.getProducer(session);
	}

	@PreDestroy
	public void preDestroy() {
		try {
			session.close();
		} catch (JMSException e) {
		}
	}

	@Override
	public void sendMessage(MessagePOJO newMessage) {
		try {
			Message msg = session.createTextMessage();
			msg.setStringProperty("subject", newMessage.getSubject());
			msg.setStringProperty("reciver", newMessage.getReciver());
			msg.setStringProperty("sender", newMessage.getSender());
			msg.setStringProperty("header", newMessage.getHeader());
			msg.setLongProperty("creationDate", newMessage.getCreationDate().getTime());
			
			defaultProducer.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
