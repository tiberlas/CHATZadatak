package dataBaseService.activeUsers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import model.ActiveUserPOJO;

@Singleton
public class ActiveUsersDataBase implements ActiveUsersDataBaseLocal {

	/**
	 * spisak svi aktivnih korisnika(agenata) na nivou celog sistema;
	 * spisak je formata ime agenta i ime na kom hosu je aktivan
	 * */
	private List<ActiveUserPOJO> activeAgents  = new ArrayList<ActiveUserPOJO>();

	@Override
	public List<ActiveUserPOJO> getActiveAgents() {
		return activeAgents;
	}

	@Override
	public ActiveUserPOJO getActiveAgent(String username) {
		
		for(ActiveUserPOJO user : activeAgents) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	@Override
	public void setActiveAgents(List<ActiveUserPOJO> activeAgents) {
		this.activeAgents = activeAgents;
	}

	@Override
	public void addAgent(ActiveUserPOJO newActiveAgent) {
		this.activeAgents.add(newActiveAgent);
	}
	
	@Override
	public void removeAgent(ActiveUserPOJO activeAgent) {
		this.activeAgents.remove(activeAgent);
	}
	
	@Override
	public void addAgents(List<ActiveUserPOJO> activeAgents) {
		this.activeAgents.addAll(activeAgents);
	}
	
	@Override
	public void removeAgents(List<ActiveUserPOJO> agents) {
		this.activeAgents.removeAll(agents);
	}
	
	@Override
	public List<String> removeAgentsOnHost(String hostName) {
		List<String> removedUsers = new ArrayList<String>();
		
		this.activeAgents.forEach(user -> {
			if(user.getHostName().equals(hostName)) {
				removedUsers.add(user.getUsername());
				this.activeAgents.remove(user);
			}
		});
		
		return removedUsers;
	}
	
	@Override
	public void cleanUp() {
		activeAgents.clear();
	}
}
