package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import axis2swing.ui.Axis2SwingUIController;

public class EngageModuleGloballyPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public EngageModuleGloballyPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Engage Module Globally");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String message = "<html><p>To engage a module on all services across the system,"
				+ "select a module from the combo box below and click on the \"Engage\" button."
				+ "Any module that needs to place handlers into the pre-dispatch phase needs to be engaged"
				+ "globally.</p></html>";

		JLabel newLabel = new JLabel(message);
		add(newLabel);

		Collection lstModule = controller.getAvailableModules();
		if (lstModule != null && !lstModule.isEmpty())
		{
			JPanel newPanel = new JPanel();
			newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			newPanel.setBackground(Color.white);
			newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
			add(newPanel);

			newLabel = new JLabel("Select Module");

			String[] moduleNames = getModuleNames(lstModule);

			ComboBoxModel cmbModuleModel = new DefaultComboBoxModel(moduleNames);

			final JComboBox cmbModule = new JComboBox();
			cmbModule.setAlignmentX(Component.LEFT_ALIGNMENT);
			cmbModule.setMaximumSize(new Dimension(300, 40));
			cmbModule.setModel(cmbModuleModel);
			newPanel.add(cmbModule);

			JButton newButton = new JButton("Engage");
			newButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					if (controller.engageModuleGlobally(cmbModule
							.getSelectedItem().toString()))
					{
						JOptionPane.showMessageDialog(null,
								"Module successfully engaged.",
								"Engage Module Globally",
								JOptionPane.INFORMATION_MESSAGE);

						cmbModule.setSelectedIndex(0);
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Error engaging module.",
								"Engage Module Globally",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			newPanel.add(newButton);
		}
		else
		{
			newLabel = new JLabel("No available module");
			add(newLabel);
		}
	}
}
