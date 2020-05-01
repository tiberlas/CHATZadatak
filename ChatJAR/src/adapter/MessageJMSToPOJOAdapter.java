package adapter;

import java.util.Calendar;

import javax.jms.JMSException;
import javax.jms.Message;

import model.MessagePOJO;

public abstract class MessageJMSToPOJOAdapter {

	/**
	 * Converts JMS message to my MessagePOJO
	 * */
	
	public static MessagePOJO convertFromJmsToPojo(Message message) {

		try {
			System.out.println("recived a message from " + message.getStringProperty("sender"));
			System.out.println("to " + message.getStringProperty("reciver"));
			System.out.println("header " + message.getStringProperty("header"));
			System.out.println("content " + message.getStringProperty("subject"));
			System.out.println(message.getLongProperty("creationDate"));
			System.out.println("--------------------------------");
			
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(message.getLongProperty("creationDate"));
			
			
			MessagePOJO msg = new MessagePOJO(
					message.getStringProperty("reciver"),
					message.getStringProperty("sender"),
					message.getStringProperty("header"),
					message.getStringProperty("subject"),
					date.getTime()
					);
			
			return msg;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
