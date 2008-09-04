package axis2swing.data;

import java.util.LinkedList;
import java.util.List;

public class ServiceGroup
{
	private String name;
	private List<Service> lstService;
	private List<Module> lstModule;

	public ServiceGroup(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void addService(Service service)
	{
		if (lstService == null)
			lstService = new LinkedList<Service>();

		lstService.add(service);
	}

	public void setServices(List<Service> lstService)
	{
		this.lstService = lstService;
	}

	public List<Service> getServices()
	{
		return this.lstService;
	}

	public void addModule(Module module)
	{
		if (lstModule == null)
			lstModule = new LinkedList<Module>();

		lstModule.add(module);
	}

	public void setModules(List<Module> lstModule)
	{
		this.lstModule = lstModule;
	}

	public List<Module> getModules()
	{
		return this.lstModule;
	}
}