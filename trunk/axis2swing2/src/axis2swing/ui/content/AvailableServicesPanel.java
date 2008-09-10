package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;

import java.util.Iterator;

import axis2swing.ui.Axis2SwingUIController;

public class AvailableServicesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public AvailableServicesPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		Collection lstService = controller.getAvailableServices();

		if (lstService != null && !lstService.isEmpty())
		{
			setHeader("Available Services");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			for (Iterator iterator = lstService.iterator(); iterator.hasNext();) {
				displayService((AxisService)iterator.next());
			}
				
		}
		else
		{
			setHeader("No available service");
		}
	}

	private void displayService(AxisService service)
	{
		String message = "<html><h2><font color=\"blue\">"
				+ service.getName()
				+ "</font></h2>"
				+ "<font color=\"blue\">Service EPR : </font><font color=\"black\">"
				+ service.getEndpointURL() + "</font><br>"
				+ "<h4>Service Description : <font color=\"black\">"
				+ service.getDocumentation() + "</font></h4>"
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

		if (service.getOperations() != null)
		{
			displayOperations(service);
		}
	}

	private void displayModules(AxisService service)
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
			AxisModule module = (AxisModule) service.getModules().get(i);
			final String moduleName = module.getName();
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

	private void displayOperations(AxisService service)
	{
		Iterator lstOperation = service.getOperations();

		String message = "<html><br><i>Available operations</i></html>";

		JLabel newLabel = new JLabel(message);
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		while (lstOperation.hasNext())
		{
			AxisOperation operation = (AxisOperation)lstOperation.next();
			message = "<html><ul><li>" + operation.getName()
					+ "</li></ul></html>";

			newLabel = new JLabel(message);
			newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(newLabel);

			if (operation.getEngagedModules() != null
					&& !operation.getEngagedModules().isEmpty())
			{
				message = "<html><i>Engaged Modules for the Operation</i></html>";

				newLabel = new JLabel(message);
				newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				newLabel
						.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
				add(newLabel);

				final String serviceName = service.getName();
				final String operationName = operation.getName().getLocalPart();

				for (Iterator opModules = operation.getEngagedModules().iterator(); opModules.hasNext();)
				{
					AxisModule module = (AxisModule) opModules.next();
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
