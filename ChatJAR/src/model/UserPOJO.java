package model;

import java.io.Serializable;

public class UserPOJO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String host;
	
	public UserPOJO() {
		super();
	}
	
	public UserPOJO(String username, String password, String host) {
		super();
		this.username = username;
		this.password = password;
		this.host = host;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + ", host=" + host + "]";
	}

}