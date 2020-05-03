package agents.http;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.HostPOJO;
import model.MessagePOJO;

public abstract class SendMessageToHost {

	public static void sendPrivateMessage(HostPOJO reciverhost, MessagePOJO message) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverhost.getIpAddress()+":"+reciverhost.getPortNumber()+"/ChatWAR/chat-rest/messages/user");
		target.request().post(Entity.entity(message, MediaType.APPLICATION_JSON));
	}
	
	public static void sendPublicMessage(HostPOJO reciverhost, MessagePOJO message) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverhost.getIpAddress()+":"+reciverhost.getPortNumber()+"/ChatWAR/chat-rest/messages/user-agents-only");
		target.request().post(Entity.entity(message, MediaType.APPLICATION_JSON));
	}
}
