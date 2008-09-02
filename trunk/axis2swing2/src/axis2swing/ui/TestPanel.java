package axis2swing.ui;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class TestPanel extends javax.swing.JPanel {
	private JLabel lblHeader;
	private JTextPane txtInstruction;
	private JLabel lblSelect;
	private JComboBox cmbModule;
	private JButton btnEngage;
	private JComboBox cmbService;
	private JButton btnDeactivate;
	private JLabel lblSelectService;
	private JComboBox cmbServiceGroup;
	private JLabel lblSelectGroup;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TestPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public TestPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(1000, 300));
			this.setLayout(null);
			{
				lblHeader = new JLabel();
				lblHeader.setLayout(null);
				this.add(lblHeader);
				lblHeader.setText("Deactivate Service");
				lblHeader.setBounds(12, 12, 478, 15);
				lblHeader.setFont(new java.awt.Font("Dialog",1,20));
			}
			{
				txtInstruction = new JTextPane();
				this.add(txtInstruction);
				txtInstruction.setText("Only the services that are active are listed below. Note that although you can activate a service from this page, once system is restarted the service will be active again");
				txtInstruction.setBounds(12, 39, 976, 37);
			}
			{
				lblSelectService = new JLabel();
				this.add(lblSelectService);
				lblSelectService.setText("Select Service");
				lblSelectService.setBounds(12, 88, 120, 15);
			}
			{
				ComboBoxModel cmbServiceModel = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				cmbService = new JComboBox();
				this.add(cmbService);
				cmbService.setModel(cmbServiceModel);
				cmbService.setBounds(144, 84, 247, 22);
			}
			{
				btnDeactivate = new JButton();
				this.add(btnDeactivate);
				btnDeactivate.setText("Deactivate");
				btnDeactivate.setBounds(144, 118, 74, 22);
				btnDeactivate.setSize(100, 22);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}