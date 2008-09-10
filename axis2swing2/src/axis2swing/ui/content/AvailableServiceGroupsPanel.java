package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.AxisServiceGroup;

import axis2swing.ui.Axis2SwingUIController;

public class AvailableServiceGroupsPanel extends PanelContent
{

	private static final long serialVersionUID = 1L;

	public AvailableServiceGroupsPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		Iterator lstGroup = controller.getAvailableServiceGroups();

		if (lstGroup != null)
		{
			setHeader("Available Service Groups");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			while (lstGroup.hasNext())
			{
				AxisServiceGroup theGroup = (AxisServiceGroup)lstGroup.next();
				String message = "<html><h2>" + theGroup.getServiceGroupName()
						+ "</h2></html>";

				JLabel newLabel = new JLabel(message);
				add(newLabel);
				
				Iterator services = theGroup.getServices(); 
				if ( services != null)
				{
					while (services.hasNext())
					{
						displayService((AxisService)services.next());
					}
				}

				if (theGroup.getEngagedModules() != null
						&& !theGroup.getEngagedModules().isEmpty())
				{
					message = "<html><i>Engaged modules</i></html>";

					newLabel = new JLabel(message);
					add(newLabel);

					for (Iterator modules = theGroup.getEngagedModules().iterator(); modules.hasNext();)
					{
						AxisModule module = (AxisModule) modules.next();
						message = "<html><ul><li>"
								+ module.getName()
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

	private void displayService(AxisService service)
	{
		final AxisService theService = service;

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

	private void displaySingleService(AxisService service)
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
				+ service.getEndpointURL() + "</font><br></html>";
		newLabel = new JLabel(message);
		add(newLabel);

		message = "<html><h4>Service Description : <font color=\"black\">"
				+ service.getDocumentation() + "</font></h4></html>";
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
		
		Iterator operations = service.getOperations();
		if ( operations != null)
		{
			message = "<html><i>Available operations</i></html>";
			newLabel = new JLabel(message);
			add(newLabel);

			while(operations.hasNext())
			{
				AxisOperation operation = (AxisOperation) operations.next();
				message = "<html><ul><li>"
						+ operation.getName().getLocalPart()
						+ "</li></ul></html>";
				newLabel = new JLabel(message);
				add(newLabel);
			}
		}

		setVisible(true);
	}
}
