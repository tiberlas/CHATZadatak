package ws;

import model.MessagePOJO;

public abstract class JsonEncoder {
	
	public static String messageToJson(MessagePOJO message) {
		String messageJson="{\"reciver\": \""+message.getReciver()+"\"," +
				"\"sender\": \""+message.getSender()+"\"," +
				"\"creationDate\": \""+message.getCreationDate().toString()+"\"," +
				"\"header\": \""+message.getHeader()+"\"," +
				"\"subject\": \""+message.getSubject()+"\"}";
		
		return messageJson;
	}

}
