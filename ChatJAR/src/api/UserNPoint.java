package api;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import model.UserPOJO;
import services.UserServiceLocal;

@Stateless
@Path("/users")
@LocalBean
public class UserNPoint {

	@EJB
	private UserServiceLocal userService;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerNewUsers(UserPOJO newUser, @Context UriInfo uriInfo) {
		if(userService.registerNewUser(newUser)) {
			//TODO vraca localhost:8080/user/login
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	        builder.replacePath("/users/login");
			
			return Response.created(builder.build()).build();			

		} else return Response.status(Status.NOT_ACCEPTABLE).build();
		
	}

	@GET
	@Path("/registered")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] getAllRegisteredUsers() {
		return userService.listOfAllRegistredUsers();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, UserPOJO user) {
		//TODO: cuvanje sesije da message ima ogr
		if(userService.loggin(user)) {
			
			if(request.getSession().getAttribute("logged") == null) {
				HttpSession ses = request.getSession();
				ses.setAttribute("logged", user);
			}
			
			return Response.ok("User is logged in").build();
			
		} else return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@DELETE
	@Path("/loggedIn")
	public Response logout(@Context HttpServletRequest request) {
		
		UserPOJO user = (UserPOJO) request.getSession().getAttribute("logged");
		if(userService.loggout(user.getUsername())) {
			
			request.getSession().invalidate();
			return Response.ok("Successfully logged out").build();

		} else return Response.status(Status.NOT_ACCEPTABLE).build(); 
	}
	
	@GET
	@Path("/loggedIn")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] getAllLoggedIn() {
		return userService.listOfAllLoggedInUsers();
	}
	
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public UserPOJO getTheCurrentUser(@Context HttpServletRequest request) {
		UserPOJO user = (UserPOJO) request.getSession().getAttribute("logged");
		
		return user;
	}
 	
	
}
