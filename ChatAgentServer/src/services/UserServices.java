package services;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.security.auth.spi.Users.User;

import dataBaseService.UserDataBase;

@Path("/user")
public class UserServices {

	@EJB
	private UserDataBase users;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User newUser) {
		
		if(users.addUser(newUser))
			return Response.status(Status.CREATED).build();
		else return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User userCredential, @Context HttpServletRequest request) {
		
		if(users.checkIfExist(userCredential) ) {
			request.getSession().setAttribute("user", userCredential);
			
			return Response.status(Status.OK).build();
		} else return Response.status(Status.NOT_ACCEPTABLE).build();
	}	
	
	@DELETE
	@Path("")
	public Response logout(@Context HttpServletRequest request) {
		User user = null;
		user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			request.getSession().invalidate();
			
			return Response.ok().build();
		} else return Response.status(Status.NOT_ACCEPTABLE).build();
	}
	
	@GET
	@Path("/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllLoggedInUsers(@Context HttpServletRequest request) {
		//TODO
		
		return null;
	}
	
	@GET
	@Path("/registered")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllRegistretedUsers(@Context HttpServletRequest request) {
		
		return users.getAllUsers();
	}
	
}
