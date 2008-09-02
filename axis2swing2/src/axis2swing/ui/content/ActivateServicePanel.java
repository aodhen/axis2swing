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

public class ActivateServicePanel extends PanelContent implements MouseListener{

	private JButton btnActivate;
	
	private JComboBox cmbService;
	
	private JLabel lblNoService;
	private JLabel lblResult;
	
	public ActivateServicePanel(Axis2SwingUIController controller) {
		super(controller);
	}

	@Override
	protected void loadComponent() {
		setLayout(null);
		{
			JLabel lblHeader = new JLabel();
			lblHeader.setLayout(null);
			this.add(lblHeader);
			lblHeader.setText("Activate Service");
			lblHeader.setBounds(12, 12, 478, 15);
			lblHeader.setFont(new java.awt.Font("Dialog",1,20));
		}
		{
			JTextPane txtInstruction = new JTextPane();
			this.add(txtInstruction);
			txtInstruction.setText("The services that are inactive are listed below. Although you can activate the services from this page, once system is restarted the services will be inactive again");
			txtInstruction.setBounds(12, 39, 976, 37);
		}
		{
			JLabel lblSelectService = new JLabel();
			this.add(lblSelectService);
			lblSelectService.setText("Select Service");
			lblSelectService.setBounds(12, 88, 120, 15);
		}
		{
			btnActivate = new JButton();
			this.add(btnActivate);
			btnActivate.addMouseListener(this);
			btnActivate.setText("Activate");
			btnActivate.setBounds(144, 118, 74, 22);
			btnActivate.setSize(150, 22);
		}
		{
			lblNoService = new JLabel("No inactive services present.");
			lblNoService.setBounds(144, 84, 100, 22);
			add(lblNoService);
			lblNoService.setVisible(false);
		}
		{
			List<Service> lstInactiveService = controller.getInactiveServices();
			
			if(lstInactiveService != null && !lstInactiveService.isEmpty()) {
				
				String[] serviceNames = new String[lstInactiveService.size()];
				
				for(int i = 0; i< lstInactiveService.size(); i++) {
					serviceNames[i] = lstInactiveService.get(i).getName();
				}
				
				ComboBoxModel cmbServiceModel = 
					new DefaultComboBoxModel(serviceNames);
				cmbService = new JComboBox();
				this.add(cmbService);
				cmbService.setModel(cmbServiceModel);
				cmbService.setBounds(144, 84, 247, 22);
				
				btnActivate.setEnabled(true);
				
				lblResult = new JLabel();
				lblResult.setBounds(144, 143, 800, 22);
				lblResult.setVisible(false);
				add(lblResult);
			}
			else
			{
				btnActivate.setEnabled(false);
			}
		}
		
	}
	
	private void refresh() {
		List<Service> lstInactiveService = controller.getInactiveServices();
		
		if(lstInactiveService != null && !lstInactiveService.isEmpty()) {
			
			String[] serviceNames = new String[lstInactiveService.size()];
			
			for(int i = 0; i< lstInactiveService.size(); i++) {
				serviceNames[i] = lstInactiveService.get(i).getName();
			}
			
			ComboBoxModel cmbServiceModel = 
				new DefaultComboBoxModel(serviceNames);
			
			if(lblNoService != null)
				lblNoService.setVisible(false);
			cmbService.setVisible(false);
			cmbService.setModel(cmbServiceModel);
			cmbService.setVisible(true);
			
			btnActivate.setEnabled(true);
		}
		else
		{
			cmbService.setVisible(false);
			lblNoService.setVisible(true);
			btnActivate.setEnabled(false);
		}
	}

	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JButton) {
			JButton theButton = (JButton)me.getSource();
			
			if(theButton.equals(btnActivate)) {
				if(controller.activateService(cmbService.getSelectedItem().toString())) {
					lblResult.setText(cmbService.getSelectedItem().toString() + " service activated");
				}
				else {
					lblResult.setText("Cannot activate service");
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