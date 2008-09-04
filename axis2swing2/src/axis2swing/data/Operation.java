package axis2swing.data;

import java.util.LinkedList;
import java.util.List;

public class Operation
{
	private String name;
	private List<Module> lstModule;

	public Operation(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
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