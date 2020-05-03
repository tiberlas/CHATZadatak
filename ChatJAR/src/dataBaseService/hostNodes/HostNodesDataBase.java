package dataBaseService.hostNodes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import model.HostPOJO;

@Singleton
public class HostNodesDataBase implements HostNodesDataBaseLocal {

	/**
	 * spisak svih host cvorova sa adresom tog cvora;
	 * master cvor je odvojen i ne nalazi se na spisku(listi)
	 * */
	private HostPOJO masterNode = null;
	private List<HostPOJO> hostNodes = new ArrayList<HostPOJO>();
	
	@Override
	public HostPOJO getMasterNode() {
		return masterNode;
	}
	
	@Override
	public void setMasterNode(HostPOJO masterNode) {
		this.masterNode = masterNode;
	}
	
	@Override
	public List<HostPOJO> getHostNodes() {
		return hostNodes;
	}
	
	@Override
	public void setHostNodes(List<HostPOJO> hostNodes) {
		this.hostNodes = hostNodes;
	}
	
	@Override
	public void addHostNode(HostPOJO newHostNode) {
		this.hostNodes.add(newHostNode);
	}
	
	@Override
	public void removeHostNode(HostPOJO hostNode) {
		this.hostNodes.remove(hostNode);
	}
	
	@Override
	public void addHostNodes(List<HostPOJO> hostNodes) {
		this.hostNodes.addAll(hostNodes);
	}
	
	@Override
	public void removeHostNodes(List<HostPOJO> hostNodes) {
		this.hostNodes.removeAll(hostNodes);
	}
	
	@Override
	public HostPOJO getHostNode(String hostName) {
		for(HostPOJO host: hostNodes) {
			if(host.getAlias().equals(hostName)) {
				return host;
			}
		}
		
		return null;
	}
	
	@Override
	public void cleanUp() {
		this.hostNodes.clear();
		this.masterNode = null;
	}
}
