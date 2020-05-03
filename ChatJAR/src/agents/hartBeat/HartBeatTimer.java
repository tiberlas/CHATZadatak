package agents.hartBeat;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import agents.HostAgentLocal;
import agents.http.HartBeatHttp;
import agents.http.HostNodeHttp;
import dataBaseService.hostNodes.HostNodesDataBaseLocal;

@Singleton
public class HartBeatTimer {

	@EJB
	private HostNodesDataBaseLocal hostNodes;
	
	@EJB
	private HostAgentLocal hostAgent;
	
	@Schedule(hour = "*", minute = "*", second = "*/30", info = "check other nodes")
	public void trigerHartBeat() {
		
		System.out.println("HartBeat protokol started");

		List<String> fallenNodes = new ArrayList<>();
		hostNodes.getHostNodes().forEach(host -> {
			if(!HartBeatHttp.checkNode(host)) {
				if(!HartBeatHttp.checkNode(host)) {
					fallenNodes.add(host.getAlias());
				}
			}
		});
		
		//obrisemo sve pale cvorove iz zapisa od ovog host-a
		fallenNodes.forEach(node -> {
			hostAgent.removeNewHostNode(node);
		});
		
		//preostalim cvorovima javimo da su ovi cvorovi pali
		hostNodes.getHostNodes().forEach(host -> {
			fallenNodes.forEach(node -> {
				HostNodeHttp.remove(host, node);				
			});
		});
	}
}
