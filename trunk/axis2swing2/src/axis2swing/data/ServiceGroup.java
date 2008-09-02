package axis2swing.data;

import java.util.LinkedList;
import java.util.List;


public class ServiceGroup {
	private String name;
	private List<Service> lstService;
	private List<Module> lstModules;
	
	public ServiceGroup(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void addService(Service service) {
		if(lstService == null)
			lstService = new LinkedList<Service>();
		
		lstService.add(service);
	}
	
	public void setServices(List<Service> lstService) {
		this.lstService = lstService;
	}
	public List<Service> getServices() {
		return lstService;
	}
	
	public void addModule(Module module) {
		if(lstModules == null)
			lstModules = new LinkedList<Module>();
		
		lstModules.add(module);
	}
	
	public void setModules(List<Module> lstModules) {
		this.lstModules = lstModules;
	}
	public List<Module> getModules() {
		return lstModules;
	}
	
	
}