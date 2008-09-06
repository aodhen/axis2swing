//package axis2swing.ui.content;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.BoxLayout;
//import javax.swing.ComboBoxModel;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import axis2swing.data.Module;
//import axis2swing.data.Operation;
//import axis2swing.data.Service;
//import axis2swing.ui.Axis2SwingUIController;
//
//public class EngageModuleOperationPanel extends PanelContent implements
//		ActionListener
//{
//	private static final long serialVersionUID = 1L;
//
//	private JButton btnEngage;
//	private JComboBox cmbService;
//	private JComboBox cmbModule;
//	private JComboBox cmbOperation;
//	private JLabel lblModule;
//	private JLabel lblOperation;
//	private JPanel panSelection;
//	private GridBagConstraints gbc;
//
//	public EngageModuleOperationPanel(Axis2SwingUIController controller)
//	{
//		super(controller);
//	}
//
//	@Override
//	protected void initGUI()
//	{
//		setHeader("Engage Module for an Operation");
//		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//
//		String message = "<html><p>To engage a module for an  axis operation,</p>"
//				+ "<ol>"
//				+ "<li>select the service</li>"
//				+ "<li>wait for available modules and operations</li>"
//				+ "<li>select the module you want to engage </li>"
//				+ "<li>select the axis operation you like the module to be engaged.</li>"
//				+ "<li>click \"Engage\".</li></ol></html>";
//
//		JLabel newLabel = new JLabel(message);
//		add(newLabel);
//
//		List<Service> lstService = controller.getAvailableServices();
//
//		if (lstService != null && !lstService.isEmpty())
//		{
//			panSelection = new JPanel();
//			panSelection.setAlignmentX(Component.LEFT_ALIGNMENT);
//			panSelection.setBackground(Color.white);
//			panSelection.setLayout(new GridBagLayout());
//			panSelection.setMaximumSize(new Dimension(400, 40));
//			add(panSelection);
//
//			gbc = new GridBagConstraints();
//			newLabel = new JLabel("Select Service");
//			gbc.gridx = 0;
//			gbc.gridy = 0;
//			gbc.anchor = GridBagConstraints.LINE_START;
//			gbc.fill = GridBagConstraints.HORIZONTAL;
//			panSelection.add(newLabel, gbc);
//
//			String[] serviceNames = new String[lstService.size() + 1];
//			serviceNames[0] = "<Select Service>";
//			for (int i = 0; i < lstService.size(); i++)
//				serviceNames[i + 1] = lstService.get(i).getName();
//
//			ComboBoxModel cmbServiceModel = new DefaultComboBoxModel(
//					serviceNames);
//			cmbService = new JComboBox();
//			cmbService.addActionListener(this);
//			cmbService.setPreferredSize(new Dimension(300, 40));
//			cmbService.setModel(cmbServiceModel);
//			gbc.gridx = 1;
//			gbc.gridy = 0;
//			panSelection.add(cmbService, gbc);
//		}
//		else
//		{
//			add(new JLabel("No available service"));
//		}
//	}
//
//	public void actionPerformed(ActionEvent ae)
//	{
//		if (ae.getSource().equals(cmbService))
//		{
//			if (cmbService.getSelectedIndex() == 0)
//			{
//				disableModuleOperation();
//			}
//			else
//			{
//				Service service = controller.getService(cmbService
//						.getSelectedItem().toString());
//				List<Module> lstModule = service.getModules();
//				List<Operation> lstOperation = service.getOperations();
//
//				setVisible(false);
//				panSelection.setMaximumSize(new Dimension(400, 120));
//
//				if (lstModule != null && !lstModule.isEmpty()
//						&& lstOperation != null && !lstOperation.isEmpty())
//				{
//
//					if (lblModule == null)
//					{
//						lblModule = new JLabel("Select Module");
//						lblModule.setAlignmentX(Component.LEFT_ALIGNMENT);
//						gbc.gridx = 0;
//						gbc.gridy = 1;
//						gbc.anchor = GridBagConstraints.LINE_START;
//						gbc.fill = GridBagConstraints.HORIZONTAL;
//						panSelection.add(lblModule, gbc);
//					}
//					String[] moduleNames = new String[lstModule.size()];
//
//					for (int i = 0; i < lstModule.size(); i++)
//						moduleNames[i] = lstModule.get(i).getName();
//
//					if (cmbModule == null)
//					{
//						cmbModule = new JComboBox();
//						cmbModule.setAlignmentX(Component.LEFT_ALIGNMENT);
//						cmbModule.setPreferredSize(new Dimension(300, 40));
//						gbc.anchor = GridBagConstraints.LINE_START;
//						gbc.gridx = 1;
//						gbc.gridy = 1;
//						gbc.fill = GridBagConstraints.HORIZONTAL;
//						panSelection.add(cmbModule, gbc);
//					}
//
//					ComboBoxModel cmbModuleModel = new DefaultComboBoxModel(
//							moduleNames);
//					cmbModule.setModel(cmbModuleModel);
//
//					if (lblOperation == null)
//					{
//						lblOperation = new JLabel("Select Operation");
//						lblOperation.setAlignmentX(Component.LEFT_ALIGNMENT);
//						gbc.anchor = GridBagConstraints.LINE_START;
//						gbc.gridx = 0;
//						gbc.gridy = 2;
//						gbc.fill = GridBagConstraints.HORIZONTAL;
//						panSelection.add(lblOperation, gbc);
//					}
//
//					String[] operationNames = new String[lstOperation.size()];
//
//					for (int i = 0; i < lstOperation.size(); i++)
//						operationNames[i] = lstOperation.get(i).getName();
//
//					if (cmbOperation == null)
//					{
//						cmbOperation = new JComboBox();
//						cmbOperation.setAlignmentX(Component.LEFT_ALIGNMENT);
//						cmbOperation.setPreferredSize(new Dimension(300, 40));
//						gbc.anchor = GridBagConstraints.LINE_START;
//						gbc.gridx = 1;
//						gbc.gridy = 2;
//						gbc.fill = GridBagConstraints.HORIZONTAL;
//						panSelection.add(cmbOperation, gbc);
//					}
//
//					ComboBoxModel cmbOperationModel = new DefaultComboBoxModel(
//							operationNames);
//					cmbOperation.setModel(cmbOperationModel);
//
//					if (btnEngage == null)
//					{
//						btnEngage = new JButton("Engage");
//						btnEngage.addActionListener(this);
//						btnEngage.setActionCommand("engage");
//						add(btnEngage);
//					}
//
//					lblModule.setVisible(true);
//					cmbModule.setVisible(true);
//					lblOperation.setVisible(true);
//					cmbOperation.setVisible(true);
//					btnEngage.setVisible(true);
//
//				}
//				else
//				{
//					gbc.gridx = 0;
//					gbc.gridy = 1;
//					gbc.gridwidth = 2;
//					panSelection
//							.add(
//									new JLabel(
//											"Cannot find module or operation for this service"),
//									gbc);
//				}
//				setVisible(true);
//			}
//		}
//		else if (ae.getActionCommand().equals("engage"))
//		{
//			if (controller.engageModuleOperation(cmbService.getSelectedItem()
//					.toString(), cmbModule.getSelectedItem().toString(),
//					cmbOperation.getSelectedItem().toString()))
//			{
//				JOptionPane.showMessageDialog(null,
//						"Module successfully engaged.", "Engage Module",
//						JOptionPane.INFORMATION_MESSAGE);
//				disableModuleOperation();
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(null, "Error engaging module",
//						"Engage Module", JOptionPane.ERROR_MESSAGE);
//			}
//		}
//	}
//
//	private void disableModuleOperation()
//	{
//		lblModule.setVisible(false);
//		cmbModule.setVisible(false);
//		lblOperation.setVisible(false);
//		cmbOperation.setVisible(false);
//		btnEngage.setVisible(false);
//		panSelection.setVisible(false);
//		panSelection.setMaximumSize(new Dimension(400, 40));
//		panSelection.setVisible(true);
//	}
//}

package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.modules.Module;

import axis2swing.ui.Axis2SwingUIController;

public class EngageModuleOperationPanel extends PanelContent
{

	private static final long serialVersionUID = 1L;

	public EngageModuleOperationPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Operation Specific Chains");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel newLabel = new JLabel(
				"Select an Axis service from the combo and click on the 'View Operations' button to view operation specific Chains.");
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		Collection lstService = controller.getAvailableServices();

		if (lstService != null && !lstService.isEmpty())
		{
			JPanel panSelectService = new JPanel();
			panSelectService.setAlignmentX(Component.LEFT_ALIGNMENT);
			panSelectService.setBackground(Color.white);
			panSelectService.setLayout(new FlowLayout(FlowLayout.LEADING));
			panSelectService.setMaximumSize(new Dimension(800, 40));
			add(panSelectService);

			newLabel = new JLabel("Select Service");
			panSelectService.add(newLabel);
			
			String[] serviceNames = getServiceNames(lstService);
			
			ComboBoxModel cmbServiceModel = new DefaultComboBoxModel(
					serviceNames);
			final JComboBox cmbService = new JComboBox();
			cmbService.setPreferredSize(new Dimension(300, 40));
			cmbService.setModel(cmbServiceModel);
			panSelectService.add(cmbService);

			JButton newButton = new JButton("View Operations");
			newButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					displayEngageModuleOperation(cmbService.getSelectedItem()
							.toString());
				}

			});
			add(newButton);
		}
		else
		{
			newLabel = new JLabel("No available service");
			add(newLabel);
		}
	}

	private void displayEngageModuleOperation(String serviceName)
	{
		setVisible(false);
		removeAll();

		final AxisService service = controller.getService(serviceName);

		setHeader("Engage Module for a Service Group");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String message = "<html><p>To engage a module for an  axis operation,</p>"
				+ "<ol>"
				+ "<li>select the module you want to engage </li>"
				+ "<li>select the axis operation you like the module to be engaged.</li>"
				+ "<li>click \"Engage\".</li></ol></html>";

		JLabel newLabel = new JLabel(message);
		add(newLabel);

		JPanel newPanel = new JPanel();
		newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		newPanel.setBackground(Color.white);
		newPanel.setLayout(new GridBagLayout());
		newPanel.setMaximumSize(new Dimension(800, 80));
		add(newPanel);

		Collection lstModule = service.getModules();
		Iterator lstOperation = service.getOperations();

		GridBagConstraints gbc = new GridBagConstraints();

		if (lstModule != null && !lstModule.isEmpty() && lstOperation != null)
		{
			final JComboBox cmbModule;
			final JComboBox cmbOperation;

			newLabel = new JLabel("Select Module");
			newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			newPanel.add(newLabel, gbc);

			String[] moduleNames = getModuleNames(lstModule);

			ComboBoxModel cmbModuleModel = new DefaultComboBoxModel(moduleNames);

			cmbModule = new JComboBox();
			cmbModule.setAlignmentX(Component.LEFT_ALIGNMENT);
			cmbModule.setPreferredSize(new Dimension(300, 40));
			cmbModule.setModel(cmbModuleModel);
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 1;
			gbc.gridy = 0;
			newPanel.add(cmbModule, gbc);

			newLabel = new JLabel("Select Operation");
			newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 0;
			gbc.gridy = 1;
			newPanel.add(newLabel, gbc);
			
			int i=0;
			String[] operationNames = new String[service.getOperationsNameList().size()];

			while (lstOperation.hasNext()) {
				AxisOperation operation = (AxisOperation)lstOperation.next();
				operationNames[i] = operation.getName().getLocalPart();
			}
				

			ComboBoxModel cmbGroupModel = new DefaultComboBoxModel(
					operationNames);

			cmbOperation = new JComboBox();
			cmbOperation.setAlignmentX(Component.LEFT_ALIGNMENT);
			cmbOperation.setPreferredSize(new Dimension(300, 40));
			cmbOperation.setModel(cmbGroupModel);
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 1;
			gbc.gridy = 1;
			newPanel.add(cmbOperation, gbc);

			JButton newButton = new JButton("Engage");
			newButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					if (controller.engageModuleOperation(service.getName(),
							cmbModule.getSelectedItem().toString(),
							cmbOperation.getSelectedItem().toString()))
					{
						JOptionPane.showMessageDialog(null,
								"Module successfully engaged.",
								"Engage Module",
								JOptionPane.INFORMATION_MESSAGE);
						cmbModule.setSelectedIndex(0);
						cmbOperation.setSelectedIndex(0);
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Error engaging module", "Engage Module",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			});
			add(newButton);
		}
		else
		{
			newLabel = new JLabel(
					"No available module or operation to be engaged.");
			gbc.gridx = 0;
			gbc.gridy = 0;
			newPanel.add(newLabel, gbc);
		}

		setVisible(true);
	}
}
