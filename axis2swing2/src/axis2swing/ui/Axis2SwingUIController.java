package axis2swing.ui;

import java.util.LinkedList;
import java.util.List;

import axis2swing.data.Module;
import axis2swing.data.Service;
import axis2swing.data.ServiceGroup;

public class Axis2SwingUIController {
	public boolean login(String username, char[] password) {
		//TODO login procedure
		return true;
	}
	
	public int getRole() {
		return -1;
	}
	
	public boolean uploadService(String filePath) {
		//TODO upload service
		return true;
	}
	
	public List<Service> getAvailableServices(){
		List<Service> lstService = new LinkedList<Service>();
		
		//TODO get available services
		
		//TODO remove this stub
		lstService.add(new Service("Version", "http://localhost:8080/axis2/services/Version", true));
		
		Service newService = new Service("Service 2", " http://service2", true);
		newService.addEngagedMod(new Module("EngagedMod"));
		newService.addEngagedMod(new Module("Mod 2"));
		lstService.add(newService);
		
		return lstService;
	}
	
	public List<Module> getAvailableModules() {
		
		List<Module> lstModule = new LinkedList<Module>();
		
		//TODO get available modules
		
		//TODO remove this stub
		lstModule.add(new Module("script"));
		lstModule.add(new Module("metadataExchange"));
		lstModule.add(new Module("addressing"));
		
		return lstModule;
	}
	
	public List<Module> getGloballyEngagedModules(){
		List<Module> lstModule = new LinkedList<Module>();
		
		//TODO get globally engaged modules
		
		//TODO remove this stub
		lstModule.add(new Module("script"));
		lstModule.add(new Module("metadataExchange"));
		lstModule.add(new Module("addressing"));
		
		return lstModule;
	}
	
	public void disengageModuleForService(String serviceName, String moduleName) {
		//TODO diengage module
	}
	
	public List<ServiceGroup> getAvailableServiceGroups(){
		List<ServiceGroup> lstGroup = new LinkedList<ServiceGroup>();
		
		//TODO get available service groups
		
		//TO remove this stub
		ServiceGroup newGroup = new ServiceGroup("version 1.4");
		newGroup.addService(new Service("Service", "http:Service", true));
		newGroup.addService(new Service("Service2", "s2", true));
		newGroup.addModule(new Module("addressing"));
		newGroup.addModule(new Module("another module"));
		
		lstGroup.add(newGroup);
		
		return lstGroup;
	}
	
	public boolean engageModuleGlobally(String moduleName) {
		
		//TODO engage module globally
		
		return true;
	}
	
	public boolean engageModuleForGroup(String moduleName, String groupName) {
		
		//TODO engage module for group
		
		return true;
	}
	
	public boolean engageModuleForService(String moduleName, String serviceName) {
		
		//TODO engage module for service
		
		return true;
	}
	
	public List<Service> getActiveServices(){
		List<Service> lstActiveService = new LinkedList<Service>();
		
		//TODO get active services
		
		//TODO remove this stub
		lstActiveService.add(new Service("Active Service 1", "http", true));
		lstActiveService.add(new Service("Active Service 2", "http", true));
		
		return lstActiveService;
	}
	
	public List<Service> getInactiveServices(){
		List<Service> lstInactiveService = new LinkedList<Service>();
		
		//TODO get active services
		
		//TODO remove this stub
		lstInactiveService.add(new Service("Inactive Service 1", "http", false));
		lstInactiveService.add(new Service("Inactive Service 2", "http", false));
		
		return lstInactiveService;
	}
	
	public boolean deactivateService(String serviceName) {
		//TODO deactivate service
		
		return true;
	}
	
	public boolean activateService(String serviceName) {
		//TODO deactivate service
		
		return true;
	}
	
}