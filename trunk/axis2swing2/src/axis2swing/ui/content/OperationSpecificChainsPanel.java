package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;

import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.engine.Phase;

import java.util.Iterator;

import axis2swing.ui.Axis2SwingUIController;

public class OperationSpecificChainsPanel extends PanelContent
{

	private static final long serialVersionUID = 1L;

	public OperationSpecificChainsPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Operation Specific Chains");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel newLabel = new JLabel(
				"Select an Axis service from the combo and click on the 'View' button to view service handlers.");
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

			JButton newButton = new JButton("View");
			newButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					displayOperationSpecificChains(cmbService.getSelectedItem()
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

	private void displayOperationSpecificChains(String serviceName)
	{
		setVisible(false);
		removeAll();

		setHeader("Operation Specific Chains");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		AxisService service = controller.getService(serviceName);
		Iterator lstOperation = service.getOperations();

		if (lstOperation != null)
		{
			while (lstOperation.hasNext())
			{
				AxisOperation operation = (AxisOperation)lstOperation.next();

				String message = "<html><h2>Operation Name : "
						+ operation.getName() + "</h2></html>";

				JLabel newLabel = new JLabel(message);
				add(newLabel);


				
				List<Phase> lstPhase = operation.getRemainingPhasesInFlow();
				displayPhases(lstPhase, "In Flow");

				lstPhase = operation.getPhasesInFaultFlow();
				displayPhases(lstPhase, "In Fault Flow");

				lstPhase = operation.getPhasesOutFlow();
				displayPhases(lstPhase, "Out Flow");

				lstPhase = operation.getPhasesOutFaultFlow();
				displayPhases(lstPhase, "Out Fault Flow");
				
			}
		}
		else
		{
			add(new JLabel("No available operations"));
		}

		setVisible(true);
	}

	private void displayPhases(List<Phase> lstPhase, String title)
	{
		if (lstPhase != null && !lstPhase.isEmpty())
		{
			String message = "<html><h3>" + title + "</h3></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);

			for (int i = 0; i < lstPhase.size(); i++)
			{
				Phase phase = lstPhase.get(i);
				message = "<html><ul><li>Phase Name: " + phase.getName();

				if (phase.getHandlers() != null
						&& !phase.getHandlers().isEmpty())
				{
					message += "<ul>";

					for (int j = 0; j < phase.getHandlers().size(); j++)
					{
						Handler handler = (Handler) phase.getHandlers().get(j);
						message += "<li>Handler Name: "
								+ handler.getName()
								+ "</li>";
					}

					message += "</ul></li></ul>";
				}

				message += "</ul></html>";
				newLabel = new JLabel(message);
				add(newLabel);
			}
		}
	}
}
