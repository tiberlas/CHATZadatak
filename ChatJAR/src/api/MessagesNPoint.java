package api;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.MessagePOJO;

@Stateless
@Path("/messages")
@LocalBean
public class MessagesNPoint {

	//@EJB host
	
	@POST
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendPublicMessage(MessagePOJO message) {
		//TODO
		return Response.ok().build();
	}
	
	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendPrivateMessage(MessagePOJO message) {
		//TODO
		return Response.ok().build();
	}
	
	@GET
	@Path("/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public MessagePOJO[] getAllMessages(@PathParam("user") String username) {
		MessagePOJO[] messages = new MessagePOJO[5];
		//TODO
		return messages;
	}
	
}
