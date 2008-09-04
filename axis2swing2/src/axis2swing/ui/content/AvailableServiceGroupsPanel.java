package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import axis2swing.data.Service;
import axis2swing.data.ServiceGroup;
import axis2swing.ui.Axis2SwingController;

public class AvailableServiceGroupsPanel extends PanelContent
{

	private static final long serialVersionUID = 1L;

	public AvailableServiceGroupsPanel(Axis2SwingController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		List<ServiceGroup> lstGroup = controller.getAvailableServiceGroups();

		if (lstGroup != null && !lstGroup.isEmpty())
		{
			setHeader("Available Service Groups");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			for (int i = 0; i < lstGroup.size(); i++)
			{
				ServiceGroup theGroup = lstGroup.get(i);
				String message = "<html><h2>" + theGroup.getName()
						+ "</h2></html>";

				JLabel newLabel = new JLabel(message);
				add(newLabel);

				if (theGroup.getServices() != null
						&& !theGroup.getServices().isEmpty())
				{
					for (int j = 0; j < theGroup.getServices().size(); j++)
					{
						displayService(theGroup.getServices().get(j));
					}
				}

				if (theGroup.getModules() != null
						&& !theGroup.getModules().isEmpty())
				{
					message = "<html><i>Engaged modules</i></html>";

					newLabel = new JLabel(message);
					add(newLabel);

					for (int j = 0; j < theGroup.getModules().size(); j++)
					{
						message = "<html><ul><li>"
								+ theGroup.getModules().get(i).getName()
								+ "</li></ul></html>";

						newLabel = new JLabel(message);
						add(newLabel);
					}
				}
			}
		}
		else
		{
			setHeader("No available service groups");
		}
	}

	private void displayService(Service service)
	{
		final Service theService = service;

		JPanel newPanel = new JPanel();
		newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		newPanel.setBackground(Color.white);
		newPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		newPanel.setMaximumSize(new Dimension(800, 40));
		add(newPanel);

		JLabel newLabel = new JLabel("<html><ul><li>" + service.getName()
				+ "</li></ul></html>");
		newPanel.add(newLabel);

		JButton newButton = new JButton("View");
		newButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae)
			{
				displaySingleService(theService);
			}

		});
		newPanel.add(newButton);
	}

	private void displaySingleService(Service service)
	{
		setVisible(false);
		removeAll();

		setHeader("List Single Service");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String message = "<html><h2><font color=\"blue\">" + service.getName()
				+ "</font></h2></html>";

		JLabel newLabel = new JLabel(message);
		add(newLabel);

		message = "<html><font color=\"blue\">Service EPR : </font><font color=\"black\">"
				+ service.getEpr() + "</font><br></html>";
		newLabel = new JLabel(message);
		add(newLabel);

		message = "<html><h4>Service Description : <font color=\"black\">"
				+ service.getDescription() + "</font></h4></html>";
		newLabel = new JLabel(message);
		add(newLabel);

		message = "<html><i><font color=\"blue\">Service Status : ";
		if (service.isActive())
			message += "Active";
		else
			message += "Inactive";
		message += "</font></i><br></html>";
		newLabel = new JLabel(message);
		add(newLabel);

		if (service.getOperations() != null
				&& !service.getOperations().isEmpty())
		{
			message = "<html><i>Available operations</i></html>";
			newLabel = new JLabel(message);
			add(newLabel);

			for (int i = 0; i < service.getOperations().size(); i++)
			{
				message = "<html><ul><li>"
						+ service.getOperations().get(i).getName()
						+ "</li></ul></html>";
				newLabel = new JLabel(message);
				add(newLabel);
			}
		}

		setVisible(true);
	}
}
