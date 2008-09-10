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

public class ActivateServicePanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	private JButton btnActivate;
	private JComboBox cmbInactiveService;
	private JPanel panSelectService;

	public ActivateServicePanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Activate Service");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String message = "<html><p>The services that are inactive are listed below. "
				+ "Although you can activate the services from this page, "
				+ "once system is restarted the services will be inactive again</p></html>";

		JLabel newLabel = new JLabel(message);
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(newLabel);

		panSelectService = new JPanel();
		panSelectService.setAlignmentX(Component.LEFT_ALIGNMENT);
		panSelectService.setBackground(Color.white);
		panSelectService.setLayout(new FlowLayout(FlowLayout.LEADING));
		panSelectService.setMaximumSize(new Dimension(800, 40));
		add(panSelectService);

		displayInactiveServices();
	}

	private void displayInactiveServices()
	{
		List<AxisService> lstInactiveService = controller.getInactiveServices();

		if (lstInactiveService != null && !lstInactiveService.isEmpty())
		{
			JLabel newLabel = new JLabel("Select Service");
			panSelectService.add(newLabel);

			String[] serviceNames = getServiceNames(lstInactiveService);

			ComboBoxModel cmbServiceModel = new DefaultComboBoxModel(
					serviceNames);
			cmbInactiveService = new JComboBox();
			panSelectService.add(cmbInactiveService);
			cmbInactiveService.setAlignmentX(Component.LEFT_ALIGNMENT);
			cmbInactiveService.setMaximumSize(new Dimension(300, 40));
			cmbInactiveService.setModel(cmbServiceModel);

			btnActivate = new JButton("Activate");
			btnActivate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae)
				{
					if (controller.activateService(cmbInactiveService
							.getSelectedItem().toString()))
					{
						JOptionPane.showMessageDialog(null,
								"Service successfully activated",
								"Activate Service",
								JOptionPane.INFORMATION_MESSAGE);
						refresh();
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Error activating service", "Activate Service",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			});
			add(btnActivate);
		}
		else
		{
			JLabel newLabel = new JLabel("No inactive services present");
			panSelectService.add(newLabel);
		}
	}

	private void refresh()
	{
		panSelectService.setVisible(false);
		panSelectService.removeAll();

		setVisible(false);
		remove(btnActivate);

		displayInactiveServices();

		setVisible(true);
		panSelectService.setVisible(true);
	}
}
