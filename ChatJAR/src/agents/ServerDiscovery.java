package agents;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

@Singleton
@LocalBean
public class ServerDiscovery {

	public String getHostName() {
		System.out.println("______________________________________________________________________________________________________________");
		
		List<String> potentialAddresses = listIPAddresses();
		
		for(String address: potentialAddresses) {
			checkIfMasterIsRunning(address);
		}
		
		return "master";
	}
	
	private List<String> listIPAddresses(){
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

            if(j == myIp[3]) { //skips my ip
            	continue;
            }
            
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
        
        return findedIp;
    }
	
	private void checkIfMasterIsRunning(String potentialHostAddress) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		//trying to find port number
		for(int i = 1024; i<65535; ++i) {
			
			final int port = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						ResteasyWebTarget target = client.target("http://"+potentialHostAddress+":"+port+"/ChatWAR/chat-rest/find-master");
						Response response = target.request().get();
				        String ret = response.readEntity(String.class);
				        System.out.println(ret);
						
					} catch(Exception e) {
						//thread stopped
					}
				}
			}).start();
		}
	}
}
