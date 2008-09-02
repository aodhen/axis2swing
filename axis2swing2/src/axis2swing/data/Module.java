package axis2swing.data;

public class Module {
	private String name;
	private String description;
	
	public Module(String name) {
		this.name = name;
		this.description = "module description not found";
	}
	
	public Module(String name, String description) {
		this.name = name;
		if(description.equals(""))
			description = "module description not found";
		else
			this.description = description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}