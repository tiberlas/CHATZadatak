package agents;

import java.util.List;

import dataBaseService.hostNodes.HostNodesDataBaseLocal;
import model.HostPOJO;

public abstract class CreateHostName {

	public static String create(List<HostPOJO> findedHostAgents, HostNodesDataBaseLocal hostNodes) {
		if(findedHostAgents.isEmpty()) {
			hostNodes.setMasterNode(null);
			return "master";
		} else {
			
			int maxAgentNumber = -1;
			for(HostPOJO hostAgent: findedHostAgents) {
				int agentNumber;
				if(hostAgent.getAlias() != null && !hostAgent.getAlias().trim().equals("")) {
					if(hostAgent.getAlias().equals("master")) {
						hostNodes.setMasterNode(hostAgent);
					} else {
						try {
							agentNumber = Integer.parseInt(hostAgent.getAlias().substring(9));
						} catch (Exception e) {
							agentNumber = -1;
						}
							if(agentNumber > maxAgentNumber) {
							maxAgentNumber = agentNumber;
						}
					}
				}
			}
			
			return "hostAgent"+ (++maxAgentNumber);
		}
	}
	
}
