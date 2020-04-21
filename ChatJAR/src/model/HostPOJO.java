package model;

import java.util.ArrayList;
import java.util.List;

public class HostPOJO {

	private String ip;
	private String alias;
	private List<String> activeAgentsOnThisHost;
	
	public HostPOJO(String ip, String alias) {
		super();
		this.ip = ip;
		this.alias = alias;
		this.activeAgentsOnThisHost = new ArrayList<String>();
	}	
	
	public HostPOJO(String ip, String alias, List<String> activeAgents) {
		super();
		this.ip = ip;
		this.alias = alias;
		this.activeAgentsOnThisHost = activeAgents;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public List<String> getActiveAgentsOnThisHost() {
		return activeAgentsOnThisHost;
	}
	public void setActiveAgentsOnThisHost(List<String> activeAgentsOnThisHost) {
		this.activeAgentsOnThisHost = activeAgentsOnThisHost;
	}
	public void addAgentToHost(String agentName) {
		this.activeAgentsOnThisHost.add(agentName);
	}
	public void removeAgentFromHost(String agentName) {
		this.activeAgentsOnThisHost.remove(agentName);
	}
	public void clearAgentsFromHost() {
		this.activeAgentsOnThisHost.clear();
	}
	public boolean hasAgentWithName(String agentName) {
		return this.activeAgentsOnThisHost.contains(agentName);
	}
	
}
