package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dataBaseService.UserDataBase;
import model.UserPOJO;

@Stateless
@Path("/users")
@LocalBean
public class UserBean implements UserLocal, UserRemote {

	@EJB
	private UserDataBase database;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerNewUsers(UserPOJO newUser) {
		if(database.addUser(newUser)) {
			return Response.ok("SUCCESSFULLY LOGGED IN").build();			

		} else return Response.status(Status.NOT_ACCEPTABLE).build();
		
	}

	@GET
	@Path("/registered")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserPOJO> getAllRegisteredUsers() {
		return database.getAllUsers();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserPOJO user) {
		System.out.println(user);
		System.out.println("Is in DB: " + database.checkIfExist(user));
		return Response.ok().build();
	}

	
	
}
