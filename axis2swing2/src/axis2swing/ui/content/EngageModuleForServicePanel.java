package axis2swing.ui.content;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import axis2swing.data.Module;
import axis2swing.data.Service;
import axis2swing.ui.Axis2SwingUIController;

public class EngageModuleForServicePanel extends PanelContent implements MouseListener{

	private JButton btnEngage;
	
	private JComboBox cmbModule;
	private JComboBox cmbService;
	
	private JLabel lblResult;
	
	public EngageModuleForServicePanel(Axis2SwingUIController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadComponent() {
		setLayout(null);
		
		JLabel lblHeader = new JLabel();
		lblHeader.setLayout(null);
		this.add(lblHeader);
		lblHeader.setText("Engage Module For a Service");
		lblHeader.setBounds(12, 12, 478, 15);
		lblHeader.setFont(new java.awt.Font("Dialog",1,20));
	
		JTextPane txtInstruction = new JTextPane();
		this.add(txtInstruction);
		txtInstruction.setText("To engage a module for an axis service:" +
				"\n1. select the module you want to engage" + 
				"\n2. select the axis service you like the module to be engaged." + 
				"\n3. click \"Engage\".");
		txtInstruction.setBounds(12, 39, 702, 66);
	
		JLabel lblSelectModule = new JLabel();
		this.add(lblSelectModule);
		lblSelectModule.setText("Select a Module");
		lblSelectModule.setBounds(12, 117, 124, 15);
		
		btnEngage = new JButton();
		this.add(btnEngage);
		btnEngage.addMouseListener(this);
		btnEngage.setText("Engage");
		btnEngage.setBounds(184, 181, 100, 22);
	
		List<Module> lstModule = controller.getAvailableModules();
		if(lstModule != null && !lstModule.isEmpty()) {
			
			String[] moduleNames = new String[lstModule.size()];
			
			for(int i = 0;i < lstModule.size();i++) {
				moduleNames[i] = lstModule.get(i).getName();
			}
			
			ComboBoxModel cmbModuleModel = 
				new DefaultComboBoxModel(moduleNames);
			cmbModule = new JComboBox();
			this.add(cmbModule);
			cmbModule.setModel(cmbModuleModel);
			cmbModule.setBounds(185, 113, 274, 22);
			
			btnEngage.setEnabled(true);
		}
		else {
			JLabel noModule = new JLabel("No available modules");
			noModule.setBounds(185, 133, 274, 22);
			add(noModule);
			btnEngage.setEnabled(false);
		}

		JLabel lblSelectService = new JLabel();
		this.add(lblSelectService);
		lblSelectService.setText("Select a Service");
		lblSelectService.setBounds(12, 151, 166, 15);

		List<Service> lstService = controller.getAvailableServices();
		if(lstService != null && !lstService.isEmpty()) {
		
			String[] serviceNames = new String[lstService.size()];
			
			for(int i = 0; i< lstService.size(); i++) {
				serviceNames[i] = lstService.get(i).getName();
			}
			
			ComboBoxModel cmbServiceModel = 
				new DefaultComboBoxModel(serviceNames);
			cmbService = new JComboBox();
			this.add(cmbService);
			cmbService.setModel(cmbServiceModel);
			cmbService.setBounds(184, 147, 275, 22);
			
			lblResult = new JLabel();
			lblResult.setBounds(184, 206, 800, 22);
			lblResult.setVisible(false);
			this.add(lblResult);
		}
		else {
			btnEngage.setEnabled(false);
		}
	}

	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JButton) {
			JButton theButton = (JButton)me.getSource();
			
			if(theButton.equals(btnEngage)) {
				if(controller.engageModuleForService(cmbModule.getSelectedItem().toString(), cmbService.getSelectedItem().toString())) {
					lblResult.setText(cmbModule.getSelectedItem().toString() + " module engaged to the service group successfully");
				}
				else {
					lblResult.setText("Error engaging module");
				}
				
				lblResult.setVisible(true);
			}
		}
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

}
