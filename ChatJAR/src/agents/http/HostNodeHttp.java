package agents.http;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.HostPOJO;

public class HostNodeHttp {

	public static void add(HostPOJO hostNodeResiver, HostPOJO newHostNode) {
		/**
		 * host cvor primi info da je kreiran novi host cvor
		 * */
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+hostNodeResiver.getIpAddress()+":"+hostNodeResiver.getPortNumber()+"/ChatWAR/chat-rest/node");
		target.request().post(Entity.entity(newHostNode, MediaType.APPLICATION_JSON));
	}
	
	public static void remove(HostPOJO hostNodeResiver, String removedNode) {
		/**
		 * host cvor primi info da je obrisan host cvor
		 * */
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://"+hostNodeResiver.getIpAddress()+":"+hostNodeResiver.getPortNumber()+"/ChatWAR/chat-rest/node/"+removedNode);
		target.request().delete();
	}
}
