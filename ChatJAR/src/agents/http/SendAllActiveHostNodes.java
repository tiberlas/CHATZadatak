package agents.http;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.HostPOJO;

public abstract class SendAllActiveHostNodes {

	public static boolean send(List<HostPOJO> hostNodes, HostPOJO newHostNode) {
		/**
		 * master cvor dostavi spisak ne master cvorova novom ne master cvoru
		 * */
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+newHostNode.getIpAddress()+":"+newHostNode.getPortNumber()+"/ChatWAR/chat-rest/nodes");
		Response res = target.request().post(Entity.entity(hostNodes, MediaType.APPLICATION_JSON));
	
		if(res.getStatus() == 200) {
			return true;
		} else {
			return false;
		}
	}
}
