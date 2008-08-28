package axis2swing.middleware;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.engine.AxisServer;
import org.apache.axis2.transport.SimpleAxis2Server;

public class Axis2AdminManager {
	
	private ConfigurationContext configContext;
	
	public ConfigurationContext getConfigContext() {
		return configContext;
	}

	public void setConfigContext(ConfigurationContext configContext) {
		this.configContext = configContext;
	}

	public Axis2AdminManager(ConfigurationContext configContext) {
		
		this.configContext = configContext;
		
	}

}
