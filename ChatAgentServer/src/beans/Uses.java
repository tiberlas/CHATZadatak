package beans;

public class Uses {

	private String name;
	private String password;
	
	public Uses() {
		super();
		this.name = null;
		this.password = null;
	}
	
	public Uses(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
