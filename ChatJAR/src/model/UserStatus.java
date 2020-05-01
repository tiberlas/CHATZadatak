package model;

import java.io.Serializable;

public class UserStatus implements Serializable{

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean isActive;
	
	public UserStatus() {
		super();
	}
	
	public UserStatus(String username, boolean isActive) {
		super();
		this.username = username;
		this.isActive = isActive;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
