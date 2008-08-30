package axis2swing.middleware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.deployment.util.PhasesInfo;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.AxisConfiguration;

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
	
	public boolean processLogin (String username, String password) {
		
		String adminUserName = (String) getAxisConfiguration().getParameter(
                Constants.USER_NAME).getValue();
        String adminPassword = (String) getAxisConfiguration().getParameter(
                Constants.PASSWORD).getValue();
        
        if (username.equals(adminUserName) && password.equals(adminPassword)) {
        	return true;
        }
        
		return false;
	}
	
	public HashMap getModules() {
		return getAxisConfiguration().getModules();
	}
	
	public Hashtable getFaultyModules() {
		return getAxisConfiguration().getFaultyModules();
	}
	
	public Collection getGlobalModules () {
		return getAxisConfiguration().getEngagedModules();
	}
	
	public AxisService getAxisService (String serviceName) {
		try {
			return getAxisConfiguration().getService(serviceName);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap getAxisServices() {
		return getAxisConfiguration().getServices();
	}
	
	public Hashtable getFaultyServices() {
		return getAxisConfiguration().getFaultyServices();
	}
	
	public Iterator getServiceGroups() {
		return getAxisConfiguration().getServiceGroups();
	}
	
	public PhasesInfo getPhases() {
		return getAxisConfiguration().getPhasesInfo();
	}
	
	public AxisConfiguration getAxisConfiguration() {
		return configContext.getAxisConfiguration();
	}
	
	public void processDeactiveService(String serviceName) {
		try {
			getAxisConfiguration().stopService(serviceName);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processActiveService(String serviceName) {
		try {
			getAxisConfiguration().startService(serviceName);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean processUploadFile (String serviceFileSource) {
		
		 if (!(serviceFileSource.endsWith(".jar") || serviceFileSource.endsWith(".aar"))) {
			 return false;
		 }
		 else {
			 String serviceDirectory = getAxisConfiguration().getRepository().getFile() + "services/";
				
				String serviceFileNameOnly;
		        
				if (serviceFileSource.indexOf("\\") < 0) {
		        	serviceFileNameOnly =
		        		serviceFileSource.substring(serviceFileSource.lastIndexOf("/") + 1, serviceFileSource
		                            .length());
		        } else {
		        	serviceFileNameOnly =
		        		serviceFileSource.substring(serviceFileSource.lastIndexOf("\\") + 1, serviceFileSource
		                            .length());
		        }
		        
		        String serviceFileDestination = serviceDirectory + serviceFileNameOnly;
		        
				try {
					InputStream in = new FileInputStream(new File (serviceFileSource));
					OutputStream out = new FileOutputStream(new File (serviceFileDestination));
				    
			        // Transfer bytes from in to out
			        byte[] buf = new byte[1024];
			        int len;
			        while ((len = in.read(buf)) > 0) {
			            out.write(buf, 0, len);
			        }
			        in.close();
			        out.close();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
		 }
		
            
	}
}
