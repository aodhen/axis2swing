package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.engine.Phase;

import java.util.Iterator;

import axis2swing.ui.Axis2SwingUIController;

public class EditServiceParametersPanel extends PanelContent
{

	private static final long serialVersionUID = 1L;

	public EditServiceParametersPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Service Parameters");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel newLabel = new JLabel(
				"Select an Axis service from the combo and click on the 'View' button to view service parameters.");
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
					displayServiceParams(cmbService.getSelectedItem()
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

	private void displayServiceParams(String serviceName)
	{
		setVisible(false);
		removeAll();

		setHeader("Service Parameters for " + serviceName);
		setLayout(new GridBagLayout());

		AxisService service = controller.getService(serviceName);
		
		ArrayList parameters = service.getParameters();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		
		int gridCounter = 2;
		
		add (new JLabel("<html><br></html>"), gbc);
		
		for(int i=0; i < parameters.size(); i++) {
			
			Parameter param = (Parameter)parameters.get(i);
			JLabel lbl = new JLabel(param.getName() + ": ");
			gbc.anchor = GridBagConstraints.LINE_END;
			gbc.gridx = 0;
			gbc.gridy = i+gridCounter;
			gbc.fill = GridBagConstraints.NONE;
			add(lbl, gbc);

			final JTextField txtField = new JTextField();
			txtField.setPreferredSize(new Dimension(300, 22));
			txtField.setText((String)param.getValue());
			gbc.gridx = 1;
			gbc.gridy = i+gridCounter;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(txtField, gbc);
		}
		
		gridCounter = gridCounter + parameters.size();
		
		Iterator lstOperation = service.getOperations();

		while (lstOperation.hasNext()) {
			AxisOperation operation = (AxisOperation) lstOperation.next();
			ArrayList opParams = operation.getParameters();
			
			gbc.anchor = GridBagConstraints.LINE_END;
			gbc.gridx = 0;
			gbc.gridy = gridCounter + 1;
			gbc.fill = GridBagConstraints.NONE;
			add(new JLabel("Operation: " + operation.getName().getLocalPart()), gbc);
			
			gbc.anchor = GridBagConstraints.LINE_END;
			gbc.gridx = 0;
			gbc.gridy = gridCounter+ 2;
			gbc.fill = GridBagConstraints.NONE;
			add (new JLabel("<html><br></html>"), gbc);
			
			gridCounter = gridCounter + 2;
			for(int i=0; i <opParams.size(); i++) {
				Parameter param = (Parameter)opParams.get(i);
				JLabel lbl = new JLabel(param.getName() + ": ");
				gbc.anchor = GridBagConstraints.LINE_END;
				gbc.gridx = 0;
				gbc.gridy = i+gridCounter;
				gbc.fill = GridBagConstraints.NONE;
				add(lbl, gbc);

				final JTextField txtField = new JTextField();
				txtField.setPreferredSize(new Dimension(300, 22));
				txtField.setText((String)param.getValue());
				gbc.gridx = 1;
				gbc.gridy = i+gridCounter;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				add(txtField, gbc);
			}
			
			
			gridCounter = gridCounter + opParams.size();
		}

		setVisible(true);
	}

	
}
