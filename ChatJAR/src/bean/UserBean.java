package bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.UserPOJO;

@Stateless
@Path("/users")
@LocalBean
public class UserBean implements UserLocal, UserRemote {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserPOJO user) {
		System.out.println(user);
		return Response.ok().build();
	}

	public Response loggedIn() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
