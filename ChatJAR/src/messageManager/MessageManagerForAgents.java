package messageManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Stateless
@LocalBean
public class MessageManagerForAgents {

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

	public void sendMessage(MessageMetaData msg) {
		try {
			defaultProducer.send(createTextMessage(msg));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private Message createTextMessage(MessageMetaData amsg) {
		Message msg = null ;
		try {
			msg = session.createTextMessage();
			for(String property : amsg.userArgs.keySet()) {
				msg.setObjectProperty(property, amsg.userArgs.get(property));
			}
			return msg;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return msg;
	}

	public Session getSession() {
		return factory.getSession();
	}

	public MessageConsumer getConsumer() {
		return factory.getConsumer(session);
	}
	
}
