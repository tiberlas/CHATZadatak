package model;

public class HostPOJO {

	private String ipAddress;
	private int portNumber;
	private String alias;
	
	public HostPOJO(String ip, int port, String alias) {
		this.alias = alias;
		this.ipAddress = ip;
		this.portNumber = port;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "HostPOJO [ipAddress=" + ipAddress + ", portNumber=" + portNumber + ", alias=" + alias + "]";
	}

	
}
