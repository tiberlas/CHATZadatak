package messageManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageMetaData implements Serializable{

	/**
	 *It is used for mapping all properties in the text message(JMS) 
	 */
	private static final long serialVersionUID = 1L;

	public Map<String, Serializable> userArgs;
	
	public MessageMetaData() {
		userArgs = new HashMap<>();
	}
}
