package model;

import java.io.Serializable;

public class ActiveUserPOJO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String hostName;
	
	public ActiveUserPOJO() {
		super();
	}
	
	public ActiveUserPOJO(String username, String hostName) {
		super();
		this.username = username;
		this.hostName = hostName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	
	
}
