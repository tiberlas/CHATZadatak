package services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import agentManager.AgentManagerLocal;
import dataBaseService.existingUsers.UserDataBaseLocal;
import model.UserPOJO;

@Stateless
public class UserService implements UserServiceLocal{

	/**
	 * Service for users provides login, logout and register mechanism
	 * */
	
	@EJB
	private UserDataBaseLocal database;
	
	@EJB
	private AgentManagerLocal agentManager;
	
	@Override
	public boolean registerNewUser(UserPOJO user) {
		if(user != null && user.getUsername()!=null && !user.getUsername().trim().equals("")) {

			return database.addUser(user);
			
		} return false;
	}

	@Override
	public boolean loggin(UserPOJO user) {
		if(database.checkIfExist(user)) {
			
			agentManager.startAgent(user.getUsername());
			return true;
			
		} else return false;
	}

	@Override
	public boolean loggout(String username) {
		UserPOJO user = database.findUser(username);
		if(user != null) {
			
			agentManager.stopAgent(username);
			return true;
		
		} else return false;
	}

	@Override
	public String[] listOfAllLoggedInUsers() {
		return agentManager.getAgents();
	}

	@Override
	public String[] listOfAllRegistredUsers() {
		String[] allUsers = new String[database.getNumberOfUsers()];
		
		int i = 0;
		for(UserPOJO user : database.getAllUsers()) {
			allUsers[i++] = user.getUsername();
		}
		
		return allUsers;
	}

	
}
