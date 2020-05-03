package agents;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.HostPOJO;

@Singleton
@LocalBean
public class ServerDiscovery {

	public List<HostPOJO> getHosts() {
		
		List<String> potentialAddresses = listIPAddresses();
		
		//!!!DANGER
		potentialAddresses.add("127.0.0.1");
		
		List<HostPOJO> findedHostAgents = new ArrayList<HostPOJO>();
		for(String address: potentialAddresses) {
			findedHostAgents.addAll(checkIfMasterIsRunning(address));
		}
			
		return findedHostAgents;
	}
	
	private List<String> listIPAddresses() {
		/**
		 * hardcoded search for ip addresses on my local machine that might have Chat server 
		 * */
		List<String> findedIp = new ArrayList<String>();
		final byte[] myIp;
		final byte[] searchIp;
        
		try {
            myIp = Inet4Address.getLocalHost().getAddress();
            searchIp = myIp;
        } catch (Exception e) {
            return null;
        }

        for(int i=1;i<=254;++i) {
        	
            final int j = i;  // i as non-final variable cannot be referenced from inner class
            
            new Thread(new Runnable() {   // new thread for parallel execution
                
            	@Override
            	public void run() {
                    try {
                        searchIp[3] = (byte)j;
                        InetAddress address = InetAddress.getByAddress(searchIp);
                        String output = address.toString().substring(1);
                        if (address.isReachable(5000)) {
                            System.out.println(output + " is on the network");
                            findedIp.add(output);
                        } 
                    } catch (Exception e) {
                       //thread stopped
                    }
                }
            }).start();
        }
        
        try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        return findedIp;
    }
	
	private List<HostPOJO> checkIfMasterIsRunning(String potentialHostAddress) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		List<HostPOJO> findedHost = new ArrayList<HostPOJO>();
		
		//!!!DANGER
		int[] posiblePorts = new int[] {8080, 8081, 8082, 8083, 9090, 9091, 9092, 9093};
		
		ExecutorService es = Executors.newCachedThreadPool();
		for(int portNumber : posiblePorts) {
  
			es.execute(new Runnable() {
                
            	@Override
            	public void run() {
            		try {
                    	System.out.println("try: "+potentialHostAddress+":"+portNumber);
                    	ResteasyWebTarget target = client.target("http://"+potentialHostAddress+":"+portNumber+"/ChatWAR/chat-rest/identify");
                		Response response = target.request().get();
                		if(response.getStatus() == 200) {
                			String ret = response.readEntity(String.class);
                			
                			System.out.println("got response: "+ ret);
                			findedHost.add(new HostPOJO(potentialHostAddress, portNumber, ret));    			
                		}
            			} catch(Exception e) {
            				System.out.println("no response: "+potentialHostAddress+portNumber);
            			}
                }
            });
        }
		
		//ceka dok se sve niti ne zavrsi tj maksimalno 3 sekunde
		es.shutdown();
		try {
			es.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return findedHost;
	}
}
