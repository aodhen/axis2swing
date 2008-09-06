package axis2swing.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.axis2.description.AxisModule;
import org.apache.axis2.description.AxisService;

import axis2swing.ui.Axis2SwingUIController;

public abstract class PanelContent extends JPanel
{
	private static final long serialVersionUID = 1L;

	protected Axis2SwingUIController controller;

	public PanelContent(Axis2SwingUIController controller)
	{
		this.controller = controller;
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(null);
		initGUI();
	}

	protected abstract void initGUI();

	protected void setHeader(String text)
	{
		JLabel lblHeader = new JLabel(text);
		lblHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
		Font font = lblHeader.getFont();
		font = new Font(font.getName(), Font.BOLD, 20);
		lblHeader.setFont(font);
		add(lblHeader, BorderLayout.PAGE_START);
	}

	protected String[] getServiceNames(Collection lstService) {
		int i = 0;
		String[] serviceNames = new String[lstService.size()];
		for (Iterator services = lstService.iterator(); services.hasNext();) {
			AxisService service = (AxisService) services.next();
			serviceNames[i] = service.getName();
			i++;
		}
		return serviceNames;
	}
	
	protected String[] getModuleNames(Collection lstModule) {
		int i = 0;
		String[] moduleNames = new String[lstModule.size()];
		for (Iterator modules = lstModule.iterator(); modules.hasNext();) {
			AxisModule module = (AxisModule) modules.next();
			moduleNames[i] = module.getName();
			i++;
		}
		return moduleNames;
	}
	
	protected String[] getOperationNames(Collection lstModule) {
		int i = 0;
		String[] moduleNames = new String[lstModule.size()];
		for (Iterator modules = lstModule.iterator(); modules.hasNext();) {
			AxisModule module = (AxisModule) modules.next();
			moduleNames[i] = module.getName();
			i++;
		}
		return moduleNames;
	}
}
