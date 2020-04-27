package ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.MessagePOJO;

@Singleton
@ServerEndpoint("/messages/ws")
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
		System.out.println("WS agent: " + agentName);
		
		if(sessions.contains(ses)) {
			sessionAgentMap.put(agentName, ses);
		}
	}
	
	public void echoTextMessage(String message) {
		System.out.println("ECHO: " + message);
	}
	
	public void sendMessage(MessagePOJO msg) {
		try {
			Session s = sessionAgentMap.get(msg.getReciver());
	        s.getBasicRemote().sendObject(msg);
		
		} catch (IOException e) {
			System.out.println("SOCKET ERROR!");
			//e.printStackTrace();
		} catch (EncodeException e) {
			System.out.println("MESSAGE ERROR!");
			//e.printStackTrace();
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
