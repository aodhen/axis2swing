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

import axis2swing.data.Service;
import axis2swing.ui.Axis2SwingUIController;

public class DeactivateServicePanel extends PanelContent implements MouseListener{

	private JButton btnDeactivate;
	
	private JComboBox cmbService;
	
	private JLabel lblNoService;
	private JLabel lblResult;
	
	public DeactivateServicePanel(Axis2SwingUIController controller) {
		super(controller);
	}

	@Override
	protected void loadComponent() {
		setLayout(null);
		{
			JLabel lblHeader = new JLabel();
			lblHeader.setLayout(null);
			this.add(lblHeader);
			lblHeader.setText("Deactivate Service");
			lblHeader.setBounds(12, 12, 478, 15);
			lblHeader.setFont(new java.awt.Font("Dialog",1,20));
		}
		{
			JTextPane txtInstruction = new JTextPane();
			this.add(txtInstruction);
			txtInstruction.setText("Only the services that are active are listed below. Note that although you can activate a service from this page, once system is restarted the service will be active again");
			txtInstruction.setBounds(12, 39, 976, 37);
		}
		{
			JLabel lblSelectService = new JLabel();
			this.add(lblSelectService);
			lblSelectService.setText("Select Service");
			lblSelectService.setBounds(12, 88, 120, 15);
		}
		{
			btnDeactivate = new JButton();
			this.add(btnDeactivate);
			btnDeactivate.addMouseListener(this);
			btnDeactivate.setText("Deactivate");
			btnDeactivate.setBounds(144, 118, 74, 22);
			btnDeactivate.setSize(150, 22);
		}
		{
			lblNoService = new JLabel("No active services present.");
			lblNoService.setBounds(144, 84, 100, 22);
			add(lblNoService);
			lblNoService.setVisible(false);
		}
		{
			List<Service> lstActiveService = controller.getActiveServices();
			
			if(lstActiveService != null && !lstActiveService.isEmpty()) {
				
				String[] serviceNames = new String[lstActiveService.size()];
				
				for(int i = 0; i< lstActiveService.size(); i++) {
					serviceNames[i] = lstActiveService.get(i).getName();
				}
				
				ComboBoxModel cmbServiceModel = 
					new DefaultComboBoxModel(serviceNames);
				cmbService = new JComboBox();
				this.add(cmbService);
				cmbService.setModel(cmbServiceModel);
				cmbService.setBounds(144, 84, 247, 22);
				
				btnDeactivate.setEnabled(true);
				
				lblResult = new JLabel();
				lblResult.setBounds(144, 143, 800, 22);
				lblResult.setVisible(false);
				add(lblResult);
			}
			else
			{
				btnDeactivate.setEnabled(false);
			}
		}
		
	}
	
	private void refresh() {
		List<Service> lstActiveService = controller.getActiveServices();
		
		if(lstActiveService != null && !lstActiveService.isEmpty()) {
			
			String[] serviceNames = new String[lstActiveService.size()];
			
			for(int i = 0; i< lstActiveService.size(); i++) {
				serviceNames[i] = lstActiveService.get(i).getName();
			}
			
			ComboBoxModel cmbServiceModel = 
				new DefaultComboBoxModel(serviceNames);
			
			if(lblNoService != null)
				lblNoService.setVisible(false);
			cmbService.setVisible(false);
			cmbService.setModel(cmbServiceModel);
			cmbService.setVisible(true);
			
			btnDeactivate.setEnabled(true);
		}
		else
		{
			cmbService.setVisible(false);
			lblNoService.setVisible(true);
			btnDeactivate.setEnabled(false);
		}
	}

	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JButton) {
			JButton theButton = (JButton)me.getSource();
			
			if(theButton.equals(btnDeactivate)) {
				if(controller.deactivateService(cmbService.getSelectedItem().toString())) {
					lblResult.setText(cmbService.getSelectedItem().toString() + " service deactivated");
				}
				else {
					lblResult.setText("Cannot deactivate service");
				}
				lblResult.setVisible(true);
				refresh();
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