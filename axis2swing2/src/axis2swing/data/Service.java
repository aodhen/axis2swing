package axis2swing.data;

import java.util.LinkedList;
import java.util.List;


public class Service {
	private String name;
	private String EPR;
	private String description;
	private boolean isActive;
	private List<Module> engagedMod;
	private List<Operation> availableOp;
	
	public Service(String name, String EPR, boolean isActive) {
		this.name = name;
		this.EPR = EPR;
		this.description = "description";
		this.isActive = isActive;
		this.engagedMod = null;
		this.availableOp = null;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setEPR(String EPR) {
		this.EPR = EPR;
	}
	public String getEPR() {
		return EPR;
	}
	public void setStatus(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getStatus() {
		return isActive;
	}
	
	public void addEngagedMod(Module engagedMod) {
		if(this.engagedMod == null)
			this.engagedMod = new LinkedList<Module>();
		
		this.engagedMod.add(engagedMod);
	}
	
	public void setEngagedMod(List<Module> engagedMod) {
		this.engagedMod = engagedMod;
	}
	public List<Module> getEngagedMod() {
		return engagedMod;
	}
	public void setAvailableOp(List<Operation> availableOp) {
		this.availableOp = availableOp;
	}
	public List<Operation> getAvailableOp() {
		return availableOp;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	
}