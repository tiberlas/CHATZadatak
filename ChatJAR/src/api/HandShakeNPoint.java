package api;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("/")
@LocalBean
public class HandShakeNPoint {
	
	@GET
	@Path("find-master")
	public String getAllMessages() {
		
		return "Hello there";
	}

}
