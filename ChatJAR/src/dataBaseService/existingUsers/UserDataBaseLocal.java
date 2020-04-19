package dataBaseService.existingUsers;

import java.util.List;

import javax.ejb.Local;

import model.UserPOJO;

@Local
public interface UserDataBaseLocal {

	boolean addUser(UserPOJO newUser);
	boolean checkIfExist(UserPOJO user);
	UserPOJO findUser(String name);
	List<UserPOJO> getAllUsers();
	public int getNumberOfUsers();
}