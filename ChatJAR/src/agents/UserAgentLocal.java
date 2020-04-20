package agents;

import javax.ejb.Local;

@Local
public interface UserAgentLocal extends Agent {

	String getHostName();
}
