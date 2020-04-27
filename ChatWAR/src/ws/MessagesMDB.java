package ws;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import model.MessagePOJO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/WsMessagesTopic")
})
public class MessagesMDB implements MessageListener{
	/**
	 * koristi se topic: WsMessagesTopic
	 * */

	@EJB
	private MessagesWS ws;
	
	@Override
	public void onMessage(Message msg) {
		TextMessage tmsg = (TextMessage)msg;
		try {

			System.out.println("WS promio poruku preko JMS");
			MessagePOJO message = new MessagePOJO(
					tmsg.getStringProperty("reciver"),
					tmsg.getStringProperty("sender"),
					tmsg.getStringProperty("header"),
					tmsg.getStringProperty("subject"),
					new Date()
					);
			
			ws.sendMessage(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
