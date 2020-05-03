package agents.http;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.ActiveUserPOJO;
import model.HostPOJO;

public abstract class ActiveUsersHttp {

	public static boolean sendAllActiveUsers(List<ActiveUserPOJO> agents, HostPOJO reciverHost) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverHost.getIpAddress()+":"+reciverHost.getPortNumber()+"/ChatWAR/chat-rest/users/loggedIn");
		Response res = target.request().post(Entity.entity(agents, MediaType.APPLICATION_JSON));
	
		if(res.getStatus() == 200) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void sendActiveUser(ActiveUserPOJO newUser, HostPOJO reciverHost) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverHost.getIpAddress()+":"+reciverHost.getPortNumber()+"/ChatWAR/chat-rest/user/loggedIn");
		target.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
	}
	
	public static void sendInActiveUser(ActiveUserPOJO user, HostPOJO reciverHost) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverHost.getIpAddress()+":"+reciverHost.getPortNumber()+"/ChatWAR/chat-rest/user/loggedOut");
		target.request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
	}
	
	public static void sendAllInActiveUser(List<ActiveUserPOJO> users, HostPOJO reciverHost) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverHost.getIpAddress()+":"+reciverHost.getPortNumber()+"/ChatWAR/chat-rest/users/loggedOut");
		target.request().post(Entity.entity(users, MediaType.APPLICATION_JSON));
	}
}
