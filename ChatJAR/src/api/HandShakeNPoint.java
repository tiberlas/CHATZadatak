package api;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import agents.HostAgentLocal;
import dataBaseService.activeUsers.ActiveUsersDataBaseLocal;
import model.ActiveUserPOJO;
import model.HostPOJO;
import ws.MessagesWS;

@Stateless
@Path("/")
@LocalBean
public class HandShakeNPoint {
	
	@EJB
	private HostAgentLocal host;
	
	@EJB
	private ActiveUsersDataBaseLocal activeUsers;
	
	@EJB
	private MessagesWS ws;
	
	@GET
	@Path("identify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAllMessages() {
		
		return Response.ok(host.getAgentId()).build();
	}
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HostPOJO registerHostAgent(String hostName, @Context UriInfo uriInfo) {
		
		if(host.getAgentId().equals("master")) {
			String path = uriInfo.getAbsolutePath().toString();
			String[] ipAndPort = (path.split("/")[2]).split(":");
			int port;
			
			try {
				port = Integer.parseInt(ipAndPort[1]);
			} catch (NumberFormatException e) {
				e.getStackTrace();
				port = 8080;
			}
			
			host.registerHostNode(new HostPOJO(ipAndPort[0], port, hostName));
			
			return null;
		} else {

			return host.getMaster();
		}		
	}
	
	@POST
	@Path("node")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewHostNode(HostPOJO newHostNode) {
		host.addNewHostNode(newHostNode);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("nodes")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewHostNodes(List<HostPOJO> newHostNode) {
		host.addNewHostNodes(newHostNode);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("user/loggedIn")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UserLoggedIn(ActiveUserPOJO user) {
		activeUsers.addAgent(user);
		ws.addAgent(user.getUsername());
		
		return Response.ok().build();
	}
	
	@POST
	@Path("user/loggedOut")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UserLoggedOut(ActiveUserPOJO user) {
		activeUsers.removeAgent(user);
		ws.removeAgent(user.getUsername());
		
		return Response.ok().build();
	}
	
	@POST
	@Path("users/loggedIn")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UsersLoggedIn(List<ActiveUserPOJO> users) {
		activeUsers.addAgents(users);
		
		users.forEach(user -> {
			ws.addAgent(user.getUsername());
		});
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("node/{hostName}")
	public Response removeHostNode(@PathParam("hostName") String hostName) {
		host.removeNewHostNode(hostName);
		
		return Response.ok().build();
	}
	
}
