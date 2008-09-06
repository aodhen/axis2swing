package axis2swing.testing;

import java.io.File;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.AxisServiceGroup;

import java.util.Collection;

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
	
	public void test_get_service_get_operation () {
		AxisService service = manager.getAxisService("Version");
		assertNotNull(service);
		assertEquals("Version", service.getName());
		
		
		AxisOperation operation = service.getOperation(new QName("getVersion"));
		assertNotNull(operation);
		assertEquals("getVersion", operation.getName().getLocalPart());
		
	}
	
	public void test_engage_disengage_module_operation() {
		AxisOperation operation = manager.getAxisService("Version").getOperation(new QName("getVersion"));
		
		assertEquals(1, operation.getEngagedModules().size());

		manager.processEngageModuleOperation("Version", "getVersion", "script");
		
		assertEquals(2, operation.getEngagedModules().size());
		
		manager.processDisengageModuleOperation("Version", "getVersion", "script");
		
		assertEquals(1, operation.getEngagedModules().size());
		
		
	}
	
	public void test_activate_deactive_service() {
		AxisService service = manager.getAxisService("Version");
		assertTrue(service.isActive());
		
		manager.processDeactiveService("Version");
		assertFalse(service.isActive());
		
		manager.processActiveService("Version");
		assertTrue(service.isActive());
	}
	
	public void test_service_groups() {
		AxisServiceGroup serviceGroup = (AxisServiceGroup)manager.getServiceGroups().next();
		assertEquals("version", serviceGroup.getServiceGroupName());
	}
	
	public void test_modules() {
		Collection modules = manager.getModules();
		assertEquals(7, modules.size());
	}
}
