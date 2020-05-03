package ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.MessagePOJO;
import model.UserStatus;

@Stateless
@ServerEndpoint(value = "/ws")
@LocalBean
public class MessagesWS {

	private static Set<Session> activeSessions = new HashSet<Session>();
	private static Map<String, Session> sessionAgentMap = new HashMap<String, Session>();
	
	public MessagesWS() {
		super();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("new Session is Open");
		activeSessions.add(session);
	}
	
	@OnMessage
	public void registerAgent(Session ses, String agentName) {
		String username = agentName.replaceAll("\"", "");
		System.out.println("WS agent: " + username);
		sessionAgentMap.put(username, ses);
		
		UserStatus newUser = new UserStatus(username, true);
		try {
			for(Session s: activeSessions) {
				if(s != null && s.isOpen()) {
					s.getBasicRemote().sendText(JsonEncoder.userStatusToJson(newUser));					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeAgent(String agenyName) {
		if(sessionAgentMap.containsKey(agenyName)) {
			return;
		}
		
		Session agentSession = sessionAgentMap.remove(agenyName);

		UserStatus newUser = new UserStatus(agenyName, false);
		try {
			agentSession.close();

			for(Session s: activeSessions) {
				if(s != null && s.isOpen()) {
					s.getBasicRemote().sendText(JsonEncoder.userStatusToJson(newUser));					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addAgent(String agenyName) {
		UserStatus newUser = new UserStatus(agenyName, true);
		try {

			for(Session s: activeSessions) {
				if(s != null && s.isOpen()) {
					s.getBasicRemote().sendText(JsonEncoder.userStatusToJson(newUser));					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(MessagePOJO msg) {
		try {
			System.out.println("SENDING MESSAGE");
			Session ses = sessionAgentMap.get(msg.getReciver());
			
			if(ses != null && activeSessions.contains(ses)) {
				ses.getBasicRemote().sendText(JsonEncoder.messageToJson(msg));				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("session is closed");
		activeSessions.remove(session);
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		close(session);
		t.printStackTrace();
	}
	
}
