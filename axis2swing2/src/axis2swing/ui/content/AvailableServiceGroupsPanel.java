package axis2swing.ui.content;

import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.data.Module;
import axis2swing.data.Service;
import axis2swing.data.ServiceGroup;
import axis2swing.ui.Axis2SwingUIController;

public class AvailableServiceGroupsPanel extends PanelContent{

	public AvailableServiceGroupsPanel(Axis2SwingUIController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadComponent() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		List<ServiceGroup> lstGroup = controller.getAvailableServiceGroups();
		
		if(lstGroup == null || (lstGroup != null && lstGroup.isEmpty())) {
			setHeader("No available Service Groups");
		}
		else {
			setHeader("Available Service Groups");
			
			for(int i = 0; i < lstGroup.size(); i++) {
				ServiceGroup theGroup = lstGroup.get(i);
				
				JLabel lblGroupName = new JLabel(theGroup.getName());
				Font theFont = lblGroupName.getFont();
				theFont = new Font(theFont.getName(), Font.BOLD, theFont.getSize());
				lblGroupName.setFont(theFont);
				add(lblGroupName);
				
				List<Service> lstService = theGroup.getServices();
				
				if(lstService == null || (lstService != null && lstService.isEmpty())) {
					add(new JLabel("\t\t\tNo available services"));
				}
				else {
					for(int j = 0; j < lstService.size(); j++) {
						add(new JLabel("\t\t\t# " + lstService.get(j).getName()));
					}
				}
				
				List<Module> lstModule = theGroup.getModules();
				
				if(lstModule == null || lstModule != null && lstService.isEmpty()) {
					add(new JLabel("No available modules"));
				}
				else {
					add(new JLabel("Engaged modules"));
					
					for(int j = 0; j <lstModule.size(); j++) {
						add(new JLabel("\t\t\t# " + lstModule.get(j).getName()));
					}
				}
			}
		}
	}

}