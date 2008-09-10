package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.axis2.description.AxisService;

import axis2swing.ui.Axis2SwingUIController;

public class DeactivateServicePanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	private JButton btnDeactivate;
	private JComboBox cmbActiveService;
	private JPanel panSelectService;

	public DeactivateServicePanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Deactivate Service");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String message = "<html><p>Only the services that are active are listed below. "
				+ "Note that although you can deactivate a service from this page, "
				+ "once system is restarted the service will be active again</p></html>";

		JLabel newLabel = new JLabel(message);
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		panSelectService = new JPanel();
		panSelectService.setAlignmentX(Component.LEFT_ALIGNMENT);
		panSelectService.setBackground(Color.white);
		panSelectService.setLayout(new FlowLayout(FlowLayout.LEADING));
		panSelectService.setMaximumSize(new Dimension(800, 40));
		add(panSelectService);

		displayActiveServices();
	}

	private void displayActiveServices()
	{
		List<AxisService> lstActiveService = controller.getActiveServices();

		if (lstActiveService != null && !lstActiveService.isEmpty())
		{
			JLabel newLabel = new JLabel("Select Service");
			panSelectService.add(newLabel);

			String[] serviceNames = getServiceNames(lstActiveService);

			ComboBoxModel cmbServiceModel = new DefaultComboBoxModel(
					serviceNames);
			cmbActiveService = new JComboBox();
			panSelectService.add(cmbActiveService);
			cmbActiveService.setAlignmentX(Component.LEFT_ALIGNMENT);
			cmbActiveService.setMaximumSize(new Dimension(300, 40));
			cmbActiveService.setModel(cmbServiceModel);

			btnDeactivate = new JButton("Deactivate");
			btnDeactivate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					if (controller.deactivateService(cmbActiveService
							.getSelectedItem().toString()))
					{
						JOptionPane.showMessageDialog(null,
								"Service successfully deactivated",
								"Deactivate Service",
								JOptionPane.INFORMATION_MESSAGE);
						refresh();
					}
					else
					{
						JOptionPane
								.showMessageDialog(null,
										"Error deactivating service",
										"Deactivate Service",
										JOptionPane.ERROR_MESSAGE);
					}
				}

			});
			add(btnDeactivate);
		}

		else
		{
			JLabel newLabel = new JLabel("No active services present");
			panSelectService.add(newLabel);
		}
	}

	private void refresh()
	{
		panSelectService.setVisible(false);
		panSelectService.removeAll();

		setVisible(false);
		remove(btnDeactivate);

		displayActiveServices();

		setVisible(true);

		panSelectService.setVisible(true);
	}
}
