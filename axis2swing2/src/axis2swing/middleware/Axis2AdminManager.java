package axis2swing.middleware;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;

public class Axis2AdminManager {
	
	public Axis2AdminManager () {
		try {
			ConfigurationContext myConfigContext = 
			     ConfigurationContextFactory.createConfigurationContextFromFileSystem("/usr/local/apache-tomcat-6.0.16/webapps/axis2/WEB-INF" , "/usr/local/apache-tomcat-6.0.16/webapps/axis2/WEB-INF/conf/axis2.xml");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
