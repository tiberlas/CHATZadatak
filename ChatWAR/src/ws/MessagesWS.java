package ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.MessagePOJO;

@Singleton
@ServerEndpoint(value = "/messages/ws")
@LocalBean
public class MessagesWS {

	static List<Session> sessions = new ArrayList<Session>();
	static Map<String, Session> sessionAgentMap = new HashMap<String, Session>();
	
	public MessagesWS() {
		super();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("new Session is Open");
		if(!sessions.contains(session)) {
			sessions.add(session);
		}
	}
	
	@OnMessage
	public void registerAgent(Session ses, String agentName) {
		
		if(sessions.contains(ses)) {
			System.out.println("WS agent: " + agentName);
			sessionAgentMap.put(agentName, ses);
		}
	}
	
	public void removeAgent(String agenyName) {
		Session agentSession = sessionAgentMap.remove(agenyName);
		
		if(agentSession != null) {
			sessions.remove(agentSession);
		}
	}
	
	public void echoTextMessage(String message) {
		System.out.println("ECHO: " + message);
		for(Session s : sessions) {
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(MessagePOJO msg) {
		try {
			Session s = sessionAgentMap.get(msg.getReciver());
	        
//			JsonObject jsonMsg = (JsonObject) Json.createObjectBuilder()
//					.add("reciver", msg.getReciver())
//					.add("sender", msg.getSender())
//					.add("creationDate", msg.getCreationDate().toString())
//					.add("header", msg.getHeader())
//					.add("subject", msg.getSubject());
			
			s.getBasicRemote().sendText(JsonEncoder.messageToJson(msg));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void close(Session session) {
		sessions.remove(session);
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		sessions.remove(session);
		t.printStackTrace();
	}
	
}
