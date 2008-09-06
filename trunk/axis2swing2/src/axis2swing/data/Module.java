package axis2swing.data;

public class Module
{
	private String name;
	private String description;

	public Module(String name)
	{
		this.name = name;
		this.description = "module description not found";
	}

	public Module(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public String getName()
	{
		return this.name;
	}

	public String getDescription()
	{
		return this.description;
	}
}
