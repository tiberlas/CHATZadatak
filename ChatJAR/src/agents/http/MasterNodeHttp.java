package agents.http;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.HostPOJO;

public abstract class MasterNodeHttp {
	
	public static void registerThisNode(HostPOJO master, String MyName) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+master.getIpAddress()+":"+master.getPortNumber()+"/ChatWAR/chat-rest/register");
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(MyName, MediaType.APPLICATION_JSON));
		
	}

}
