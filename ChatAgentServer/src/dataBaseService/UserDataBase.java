package dataBaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

import org.jboss.security.auth.spi.Users.User;

@Singleton
public class UserDataBase {

	private Map<String, User> users = new HashMap<String, User>();
	
	public boolean addUser(User newUser) {
		if(newUser != null && !users.containsKey(newUser.getName())) {
			users.put(newUser.getName(), newUser);
			
			return true;
		} else return false;
	}
	
	public boolean checkIfExist(User user) {
		return users.containsKey(user.getName()) && users.get(user.getName()).getPassword().equals(user.getPassword());
	}
	
	public User findUser(String name) {
		return users.get(name);
	}
	
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		
		for(User u : users.values()) {
			userList.add(u);
		}
		
		return userList;
	}
}
