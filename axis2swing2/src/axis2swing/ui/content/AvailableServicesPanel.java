package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import axis2swing.data.Module;
import axis2swing.data.Operation;
import axis2swing.data.Service;
import axis2swing.ui.Axis2SwingController;

public class AvailableServicesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public AvailableServicesPanel(Axis2SwingController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		List<Service> lstService = controller.getAvailableServices();

		if (lstService != null && !lstService.isEmpty())
		{
			setHeader("Available Services");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			for (int i = 0; i < lstService.size(); i++)
				displayService(lstService.get(i));

		}
		else
		{
			setHeader("No available service");
		}
	}

	private void displayService(Service service)
	{
		String message = "<html><h2><font color=\"blue\">"
				+ service.getName()
				+ "</font></h2>"
				+ "<font color=\"blue\">Service EPR : </font><font color=\"black\">"
				+ service.getEpr() + "</font><br>"
				+ "<h4>Service Description : <font color=\"black\">"
				+ service.getDescription() + "</font></h4>"
				+ "<i><font color=\"blue\">Service Status : ";

		if (service.isActive())
		{
			message += "Active";
		}
		else
		{
			message += "Inactive";
		}
		message += "</font></i><br></html>";

		JLabel newLabel = new JLabel(message);
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		if (service.getModules() != null && !service.getModules().isEmpty())
		{
			displayModules(service);
		}

		if (service.getOperations() != null
				&& !service.getOperations().isEmpty())
		{
			displayOperations(service);
		}
	}

	private void displayModules(Service service)
	{
		String message = "<html><i>Engaged modules for the service</i></html>";

		JLabel newLabel = new JLabel(message);
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		JPanel newPanel = new JPanel();
		newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		newPanel.setBackground(Color.white);
		newPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		newPanel.setMaximumSize(new Dimension(800, 40));
		add(newPanel);

		final String serviceName = service.getName();

		for (int i = 0; i < service.getModules().size(); i++)
		{
			final String moduleName = service.getModules().get(i).getName();
			message = "<html><ul><li>" + moduleName + "</li></ul></html>";

			newLabel = new JLabel(message);
			newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			newPanel.add(newLabel);

			JButton newButton = new JButton("Disengage");
			newButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					if (controller.disengageModuleFromService(serviceName,
							moduleName))
					{
						JOptionPane.showMessageDialog(null,
								"Module successfully disengaged",
								"Disengage Module",
								JOptionPane.INFORMATION_MESSAGE);
						refresh();
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Error disengaging module", "Disengage Module",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			});
			newButton.setAlignmentX(Component.LEFT_ALIGNMENT);
			newPanel.add(newButton);
		}
	}

	private void displayOperations(Service service)
	{
		List<Operation> lstOperation = service.getOperations();

		String message = "<html><br><i>Available operations</i></html>";

		JLabel newLabel = new JLabel(message);
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		for (int i = 0; i < lstOperation.size(); i++)
		{
			Operation operation = lstOperation.get(i);
			message = "<html><ul><li>" + operation.getName()
					+ "</li></ul></html>";

			newLabel = new JLabel(message);
			newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(newLabel);

			if (operation.getModules() != null
					&& !operation.getModules().isEmpty())
			{
				message = "<html><i>Engaged Modules for the Operation</i></html>";

				newLabel = new JLabel(message);
				newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				newLabel
						.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
				add(newLabel);

				final String serviceName = service.getName();
				final String operationName = operation.getName();

				for (int j = 0; j < operation.getModules().size(); j++)
				{
					Module module = operation.getModules().get(i);
					final String moduleName = module.getName();

					JPanel newPanel = new JPanel();
					newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
					newPanel.setBackground(Color.white);
					newPanel.setBorder(BorderFactory.createEmptyBorder(0, 50,
							0, 0));
					newPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
					newPanel.setMaximumSize(new Dimension(800, 40));
					add(newPanel);

					message = "<html><ul><li>" + moduleName
							+ "</li></ul></html>";
					newLabel = new JLabel(message);
					newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
					newPanel.add(newLabel);

					JButton newButton = new JButton("Disengage");
					newButton.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent ae)
						{
							if (controller.disengageModuleFromOperation(
									serviceName, operationName, moduleName))
							{
								JOptionPane.showMessageDialog(null,
										"Module successfully disengaged",
										"Disengage Module",
										JOptionPane.INFORMATION_MESSAGE);
								refresh();
							}
							else
							{
								JOptionPane.showMessageDialog(null,
										"Error disengaging module",
										"Disengage Module",
										JOptionPane.ERROR_MESSAGE);
							}
						}

					});
					newButton.setAlignmentX(Component.LEFT_ALIGNMENT);
					newPanel.add(newButton);

				}
			}
		}
	}

	private void refresh()
	{
		setVisible(false);
		removeAll();
		initGUI();
		setVisible(true);
	}
}
