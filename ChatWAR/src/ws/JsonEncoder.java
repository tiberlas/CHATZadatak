package ws;

import model.MessagePOJO;
import model.UserStatus;

public abstract class JsonEncoder {
	
	public static String messageToJson(MessagePOJO message) {
		@SuppressWarnings("deprecation")
		String messageJson="{\"reciver\": \""+message.getReciver()+"\"," +
				"\"sender\": \""+message.getSender()+"\"," +
				"\"creationDate\": \""+message.getCreationDate().toGMTString()+"\"," +
				"\"header\": \""+message.getHeader()+"\"," +
				"\"subject\": \""+message.getSubject()+"\"}";
		
		return messageJson;
	}
	
	public static String userStatusToJson(UserStatus user) {
		return "{ \"username\": \""+user.getUsername()+"\","+
				"\"isActive\": "+user.isActive()+"}";
	}

}
