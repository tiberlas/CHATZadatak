package agents;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

public class WSCommunication {
	/**
	 * This bean is used to send JMS from UserAgent to WebSocket
	 * It sends the message received from agent to WS 
	 * */
	
	public void sendWS(Message msg) {
		try {
			Context context = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			
			final Topic topic = (Topic) context.lookup("jms/topic/WsMessagesTopic");
			context.close();
			
			Connection connection = cf.createConnection("guest", "guest.guest.1");
			final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			
			MessageProducer producer = session.createProducer(topic);
			producer.send((TextMessage) msg);

			producer.close();
			connection.close();
		} catch (Exception ex) {
			System.out.println("JMS ERROR in WSCommunication");
			ex.printStackTrace();
		}
	}
}
