package dataBaseService.activeUsers;

import java.util.List;

import javax.ejb.Local;

import model.ActiveUserPOJO;

@Local
public interface ActiveUsersDataBaseLocal {

	List<ActiveUserPOJO> getActiveAgents();

	void setActiveAgents(List<ActiveUserPOJO> activeAgents);

	void addAgent(ActiveUserPOJO newActiveAgent);

	void removeAgent(ActiveUserPOJO activeAgent);

	void addAgents(List<ActiveUserPOJO> activeAgents);

	void removeAgents(List<ActiveUserPOJO> agents);

	ActiveUserPOJO getActiveAgent(String username);

	List<String> removeAgentsOnHost(String hostName);

	void cleanUp();
}