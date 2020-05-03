package dataBaseService.hostNodes;

import java.util.List;

import javax.ejb.Local;

import model.HostPOJO;

@Local
public interface HostNodesDataBaseLocal {

	HostPOJO getMasterNode();

	void setMasterNode(HostPOJO masterNode);

	List<HostPOJO> getHostNodes();

	void setHostNodes(List<HostPOJO> hostNodes);

	void addHostNode(HostPOJO newHostNode);

	void removeHostNode(HostPOJO hostNode);
	void addHostNodes(List<HostPOJO> hostNodes);
	void removeHostNodes(List<HostPOJO> hostNodes);

	HostPOJO getHostNode(String hostName);

	void cleanUp();

}