package axis2swing.ui.content;

import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.data.Module;
import axis2swing.ui.Axis2SwingUIController;

public class GloballyEngagedModulePanel extends PanelContent{

	public GloballyEngagedModulePanel(Axis2SwingUIController controller) {
		super(controller);
	}

	@Override
	protected void loadComponent() {
		List<Module> lstModules = new LinkedList<Module>();
		lstModules = controller.getAvailableModules();
		
		if(lstModules == null || (lstModules != null && lstModules.isEmpty()))
			setHeader("No globally engaged modules");
		else {
			setHeader("Globally Engaged Modules");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			for(int i = 0; i<lstModules.size(); i++) {
				Module theModule = lstModules.get(i);
				
				add(new JLabel("\n# " + theModule.getName()));
			}
		}
	}

}