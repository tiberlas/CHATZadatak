package services;

import javax.ejb.Local;

import model.UserPOJO;

@Local
public interface UserServiceLocal {

	boolean registerNewUser(UserPOJO user);
	boolean loggin(UserPOJO user);
	boolean loggout(String username);
	String[] listOfAllLoggedInUsers();
	String[] listOfAllRegistredUsers();
}
