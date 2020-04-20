package dataBaseService.existingUsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import model.UserPOJO;

@Singleton
public class UserDataBase implements UserDataBaseLocal {

	/**
	 * this is just a mock of a data base; It's used to store users credentials
	 * 
	 * @author Tiberius
	 * */

	private Map<String, UserPOJO> users = new HashMap<String, UserPOJO>();

	@PostConstruct
	public void initDataBase() {
		UserPOJO fake1 = new UserPOJO("tibi", "1234");
		UserPOJO fake2 = new UserPOJO("svetlana", "qwer");

		users.put(fake1.getUsername(), fake1);
		users.put(fake2.getUsername(), fake2);
	}

	@Override
	public boolean addUser(UserPOJO newUser) {
		if(newUser != null && !users.containsKey(newUser.getUsername())) {
			users.put(newUser.getUsername(), newUser);
			
			return true;
		} else return false;
	}
	
	@Override
	public boolean checkIfExist(UserPOJO user) {
		return users.containsKey(user.getUsername()) && users.get(user.getUsername()).getPassword().equals(user.getPassword());
	}
	
	@Override
	public UserPOJO findUser(String name) {
		return users.get(name);
	}
	
	@Override
	public List<UserPOJO> getAllUsers() {
		return new ArrayList<UserPOJO>(users.values());
	}
	
	@Override
	public int getNumberOfUsers() {
		return users.size();
	}
	
	@PreDestroy
	public void destroy() {
		users.clear();
	}
	
}
