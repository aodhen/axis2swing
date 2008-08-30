package axis2swing.testing;

import java.io.File;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;

import axis2swing.middleware.Axis2AdminManager;
import junit.framework.TestCase;


public class Axis2AdminManagerTester extends TestCase {
	
	private Axis2AdminManager manager;
	public  void setUp() {
		String confLocation = "axis2-1.4/conf/axis2.xml";
		String repoLocation = "axis2-1.4/repository";
		
		ConfigurationContext configContext;
		try {
			configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(repoLocation,
			        confLocation);
			manager = new Axis2AdminManager(configContext);
			assertNotNull(manager);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test_manager_loaded () {

			assertNotNull(manager);
		
	}
	
	public void test_process_login() {
		assertTrue(manager.processLogin("admin", "axis2"));
		assertFalse(manager.processLogin("admin", "axis"));
	}
	
	
	public void test_file_upload() {
		manager.processUploadFile("testFiles/TemperatureConverter.aar");
		File uploadedFile = new File(manager.getAxisConfiguration().getRepository().getFile() + "services/" + "TemperatureConverter.aar");
		assertTrue(uploadedFile.exists());
		
		uploadedFile.delete();
	}
}
