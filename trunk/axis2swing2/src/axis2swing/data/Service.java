package axis2swing.data;

import java.util.LinkedList;
import java.util.List;

public class Service
{
	private String name;
	private String epr;
	private String description;
	private boolean isActive;
	private List<Module> lstModule;
	private List<Operation> lstOperation;

	public Service(String name, String epr, boolean isActive)
	{
		this.name = name;
		this.epr = epr;
		this.isActive = isActive;
		this.description = name;
	}

	public Service(String name, String epr, boolean isActive, String description)
	{
		this(name, epr, isActive);
		this.description = description;
	}

	public String getName()
	{
		return this.name;
	}

	public String getEpr()
	{
		return this.epr;
	}

	public String getDescription()
	{
		return this.description;
	}

	public boolean isActive()
	{
		return this.isActive;
	}

	public void setStatus(boolean isActive)
	{
		this.isActive = isActive;
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

	public void addOperation(Operation operation)
	{
		if (lstOperation == null)
			lstOperation = new LinkedList<Operation>();

		lstOperation.add(operation);
	}

	public void setOperations(List<Operation> lstOperation)
	{
		this.lstOperation = lstOperation;
	}

	public List<Operation> getOperations()
	{
		return this.lstOperation;
	}
}
