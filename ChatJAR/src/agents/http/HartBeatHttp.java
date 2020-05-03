package agents.http;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.HostPOJO;

public abstract class HartBeatHttp {

	public static boolean checkNode(HostPOJO reciverHost) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+reciverHost.getIpAddress()+":"+reciverHost.getPortNumber()+"/ChatWAR/chat-rest/node");
		Response res = target.request().get();
	
		if(res.getStatus() == 200) {
			return true;
		} else {
			return false;
		}
	}
}
