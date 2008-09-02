package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import axis2swing.data.Module;
import axis2swing.data.Service;
import axis2swing.ui.Axis2SwingUIController;

public class AvailableServicesPanel extends PanelContent{

	private static final long serialVersionUID = 1L;

	public AvailableServicesPanel(Axis2SwingUIController controller) {
		super(controller);
	}

	@Override
	protected void loadComponent() {
		List<Service> lstServices = controller.getAvailableServices();
		
		if(lstServices == null || (lstServices != null && lstServices.isEmpty())) {
			setHeader("No available services");
		}
		else {

			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			setHeader("Available Services");
			
			for(int i = 0; i < lstServices.size(); i++) {
				
				final Service theService = lstServices.get(i);
				
				JLabel lblServiceName = new JLabel(theService.getName());
				Font font = lblServiceName.getFont();
				font = new Font(font.getName(), Font.BOLD, font.getSize());
				lblServiceName.setAlignmentX(Component.LEFT_ALIGNMENT);
				lblServiceName.setFont(font);
				add(lblServiceName);
				
				JLabel lblServiceEPR = new JLabel("\t\t\tService EPR: " + theService.getEPR());
				lblServiceEPR.setAlignmentX(Component.LEFT_ALIGNMENT);
				add(lblServiceEPR);
				
				JLabel lblDescription = new JLabel();
				lblDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
				lblDescription.setText("\t\t\tDescription: " + theService.getDescription());
				add(lblDescription);
				
				if(theService.getEngagedMod() == null || (theService.getEngagedMod() != null &&
					theService.getEngagedMod().isEmpty())) {
					add(new JLabel("\t\t\tNo engaged module"));
				}
				else {
					add(new JLabel("\t\t\tEngaged modules for the services:"));
					
					for(int j = 0; j < theService.getEngagedMod().size(); j++) {
						final Module theModule = theService.getEngagedMod().get(j);
						
						JPanel panModule = new JPanel();
						panModule.setAlignmentX(Component.LEFT_ALIGNMENT);
						panModule.setBackground(Color.white);
						panModule.setLayout(new FlowLayout());
						add(panModule);
						
						JLabel theLabel = new JLabel("\t\t\t# " + theModule.getName());
						theLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panModule.add(theLabel);
						
						JButton btnDisengage = new JButton("Disengage");
						btnDisengage.setAlignmentX(Component.LEFT_ALIGNMENT);
						btnDisengage.addMouseListener(new MouseListener() {

							public void mouseClicked(MouseEvent me) {
								controller.disengageModuleForService(theService.getName(), 
										theModule.getName());
							}

							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
						});
						panModule.add(btnDisengage);
					}
				}
			}
			
		}
	}

}