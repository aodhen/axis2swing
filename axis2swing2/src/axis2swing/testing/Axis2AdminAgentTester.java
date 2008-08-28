package axis2swing.testing;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;

import axis2swing.middleware.Axis2AdminManager;
import junit.framework.TestCase;


public class Axis2AdminAgentTester extends TestCase {
	
	public static void oneTimeSetUp() {
		
	}
	
	public static void oneTimeTearDown() {
        // one-time cleanup code
    }


	public void test_loading_with_aixs2Config_and_repos_fields () {
		String confLocation = "axis2-1.4/conf/axis2.xml";
		String repoLocation = "axis2-1.4/repository";
		
		ConfigurationContext configContext;
		try {
			configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(repoLocation,
			        confLocation);
			Axis2AdminManager manager = new Axis2AdminManager(configContext);
			assertNotNull(manager);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
