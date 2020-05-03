package agents;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSException;
import javax.jms.Message;

import agents.http.ActiveUsersHttp;
import agents.http.MasterNodeHttp;
import agents.http.SendAllActiveHostNodes;
import agents.http.SendMessageToHost;
import agents.http.HostNodeHttp;
import dataBaseService.activeAgents.ActiveAgentsLocal;
import dataBaseService.activeUsers.ActiveUsersDataBaseLocal;
import dataBaseService.hostNodes.HostNodesDataBaseLocal;
import messageManager.MessageManagerForAgentsLocal;
import model.ActiveUserPOJO;
import model.HostPOJO;
import model.MessagePOJO;
import ws.MessagesWS;

@Startup
@Singleton
public class HostAgent implements HostAgentLocal {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ActiveAgentsLocal activeAgents;
	
	@EJB
	private MessageManagerForAgentsLocal messageManager;
	
	@EJB
	private ServerDiscovery serverDiscovery;
	
	@EJB
	private ActiveUsersDataBaseLocal activeUsers;
	
	@EJB
	private HostNodesDataBaseLocal hostNodes;
	
	@EJB
	private MessagesWS ws;
	
	private String myName = null;
	
	@PostConstruct
	public void setUp() {		
		List<HostPOJO> findedHosts = serverDiscovery.getHosts();
		
		myName = CreateHostName.create(findedHosts, hostNodes);
		activeAgents.addRunningAgent(myName, this);
		System.out.println("Host agent started: " + myName);
		
		if(!myName.equals("master")) {
			//sali http zahteve master cvoru
			MasterNodeHttp.registerThisNode(hostNodes.getMasterNode(), myName);
		}
	}
	
	@Override
	public void handleMessage(Message message) {
		//host je primio javnu poruku
		try {
			System.out.println("HOST recived a message from " + message.getStringProperty("sender"));
			System.out.println("content " + message.getStringProperty("subject"));
			System.out.println("-----------------------");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendPrivateMessage(MessagePOJO message) {
		if(activeAgents.checkIfAgentIsRunning(message.getReciver())) {
			messageManager.sendMessage(message);
		} else {
			//salje se odgovarajucem hostu hostu;
			HostPOJO host = hostNodes.getHostNode(message.getReciver());
			SendMessageToHost.sendPrivateMessage(host, message);
		}
	}
	
	@Override
	public void sendPublicMessage(MessagePOJO message) {
		sendMessageToAllMyActiveAgents(message);
		
		//posaljemo svim host cvorovima poruku
		hostNodes.getHostNodes().forEach(host -> {
			SendMessageToHost.sendPublicMessage(host, message);
		});
		
		
	}
	
	@Override
	public void sendMessageToAllMyActiveAgents(MessagePOJO message) {
		//posaljemo poruku svim agentima koji su registrovani na ovaj cvor
		//posalje i host cvoru poruku
		for(String reciver: activeAgents.getRunningAgentsNames()) {
			if(!reciver.equals(message.getSender())) {//nemoj sam sebi da posaljes poruku
				message.setReciver(reciver);
				messageManager.sendMessage(message);				
			}
		}
	}

	@Override
	public String getAgentId() {
		return myName;
	}
	
	@Override
	public HostPOJO getMaster() {
		return hostNodes.getMasterNode();
	}
	
	@Override
	public void registerHostNode(HostPOJO newHostNode) {
		
		if(!SendAllActiveHostNodes.send(hostNodes.getHostNodes(), newHostNode)) {
			//novi cvor nije primio poruku, pa pokusavamo opet.
			if(!SendAllActiveHostNodes.send(hostNodes.getHostNodes(), newHostNode)) {
				return;
			}
		}
		
		//javimo ostalim cvorovima da je novi cvor kreiran
		hostNodes.getHostNodes().forEach(hostNode -> {
			HostNodeHttp.add(hostNode, newHostNode);
		});
		
		//novom cvoru posaljemo spisak svih aktivnih korisnika
		if(!ActiveUsersHttp.sendAllActiveUsers(activeUsers.getActiveAgents(), newHostNode)) {
			if(!ActiveUsersHttp.sendAllActiveUsers(activeUsers.getActiveAgents(), newHostNode)) {
				removeNewHostNode(newHostNode.getAlias());//lokalno obisali
				
				//nije uspeo hand-shake protokol pa svi ostali cvorovi prisu novo dodat cvor
				hostNodes.getHostNodes().forEach(host -> {
					HostNodeHttp.remove(host, newHostNode.getAlias());
				});
			}
		}
		
		hostNodes.addHostNode(newHostNode);
	}
	
	@Override
	public void addNewHostNode(HostPOJO hostNode) {
		//kreiran je novi cvor
		hostNodes.addHostNode(hostNode);
	}
	
	@Override
	public void addNewHostNodes(List<HostPOJO> hostNode) {
		//od master cvora smo dobili spisak ostalih cvorova
		hostNodes.addHostNodes(hostNode);
	}

	@Override
	public void addActiveUser(ActiveUserPOJO user) {
		//kreiran je novi korisnik
		activeUsers.addAgent(user);
		
		//javljamo svim cvorovima da je kreiran novi korisnik
		hostNodes.getHostNodes().forEach(hostNode -> {
			if(!hostNode.getAlias().equals(myName)) {
				ActiveUsersHttp.sendActiveUser(user, hostNode);							
			}
		});
		
		if(!myName.equals("master")) {
			ActiveUsersHttp.sendActiveUser(user, getMaster());
		}
	}
	
	@Override
	public void removeActiveUser(String username) {
		ActiveUserPOJO user = activeUsers.getActiveAgent(username);
		
		hostNodes.getHostNodes().forEach(hostNode -> {
			if(!hostNode.getAlias().equals(myName)) {
				ActiveUsersHttp.sendInActiveUser(user, hostNode);							
			}
		});
		
		if(!myName.equals("master")) {
			ActiveUsersHttp.sendInActiveUser(user, getMaster());
		}
	}
	
	@Override
	public void removeNewHostNode(String hostName) {
		//cvor je poslao poruku da je obrisam pa ga obrisemo iz evidencije ovog cvora kao i sve korisnike koji su bili registrovani na obrisanom cvoru
		HostPOJO host = hostNodes.getHostNode(hostName);
		hostNodes.removeHostNode(host);
		
		List<String> removedUsers = activeUsers.removeAgentsOnHost(hostName);
		removedUsers.forEach(username -> {
			ws.removeAgent(username);
		});
	}
	
	@PreDestroy
	public void cleanUp() {
		//prilikom gasenja host noda javi svim ostalim cvorovima da obrisu ovaj cvor
		hostNodes.getHostNodes().forEach(hostNodeResiver -> {
			HostNodeHttp.remove(hostNodeResiver, myName);			
		});

		activeAgents.cleanUp();
		activeUsers.cleanUp();
		hostNodes.cleanUp();
	}
	
}
