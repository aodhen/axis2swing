package axis2swing.ui.content;

import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.data.Module;
import axis2swing.ui.Axis2SwingUIController;


public class AvailableModulesPanel extends PanelContent {

	public AvailableModulesPanel(Axis2SwingUIController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadComponent() {
		
		List<Module> lstModules = new LinkedList<Module>();
		lstModules = controller.getAvailableModules();
		
		if(lstModules == null || (lstModules != null && lstModules.isEmpty()))
			setHeader("No available modules");
		else {
			setHeader("Available Modules");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			for(int i = 0; i<lstModules.size(); i++) {
				Module theModule = lstModules.get(i);
				
				add(new JLabel("\n# " + theModule.getName() + ": " + theModule.getDescription()));
			}
		}
	
	}
	
	
}