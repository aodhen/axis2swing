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
import axis2swing.ui.Axis2SwingUIController;

public class EngageModuleGloballyPanel extends PanelContent implements MouseListener{

	private JComboBox cmbModule;
	
	private JButton btnEngage;
	
	private JLabel lblResult;
	
	public EngageModuleGloballyPanel(Axis2SwingUIController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadComponent() {
		
		setLayout(null);
	
		JLabel lblHeader = new JLabel();
		this.add(lblHeader);
		lblHeader.setText("Engage Module Globally");
		lblHeader.setBounds(12, 12, 350, 15);
		lblHeader.setFont(new java.awt.Font("Dialog",1,20));

		JTextPane txtInstruction = new JTextPane();
		this.add(txtInstruction);
		txtInstruction.setText("To engage a module on all services across the system, select a module from the combo box below and click on the \"Engage\" button. Any module that needs to place handlers into the pre-dispatch phase needs to be engaged globally.");
		txtInstruction.setBounds(12, 39, 800, 38);

		JLabel lblSelect = new JLabel();
		this.add(lblSelect);
		lblSelect.setText("Select a Module");
		lblSelect.setBounds(12, 89, 124, 15);
	
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
			cmbModule.setBounds(148, 85, 274, 22);
	
			btnEngage = new JButton();
			this.add(btnEngage);
			btnEngage.addMouseListener(this);
			btnEngage.setText("Engage");
			btnEngage.setBounds(148, 119, 100, 22);
			
			lblResult = new JLabel();
			lblResult.setBounds(148, 155, 800, 22);
			lblResult.setVisible(false);
			this.add(lblResult);
			}
		else {
			JLabel lblNoModule = new JLabel("No available modules");
			lblNoModule.setBounds(148, 85, 800, 22);
			this.add(lblNoModule);
		}
	}

	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JButton) {
			JButton theButton = (JButton)me.getSource();
			
			if(theButton.equals(btnEngage)) {
				if(controller.engageModuleGlobally(cmbModule.getSelectedItem().toString()))
					lblResult.setText(cmbModule.getSelectedItem().toString() + " module engaged successfully");
				else {
					lblResult.setText("Error engaging module.");
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