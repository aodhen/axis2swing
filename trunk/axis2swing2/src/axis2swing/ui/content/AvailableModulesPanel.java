package axis2swing.ui.content;

import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import org.apache.axis2.description.AxisModule;

import java.util.Iterator;

import axis2swing.ui.Axis2SwingUIController;

public class AvailableModulesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public AvailableModulesPanel(Axis2SwingUIController controller)
	{
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initGUI()
	{
		Collection lstModule = controller.getAvailableModules();

		if (lstModule == null || (lstModule != null && lstModule.isEmpty()))
		{
			setHeader("No available module");
		}
		else
		{
			setHeader("Available Modules");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			String message = "<html><ul>";

			
			for (Iterator modules = lstModule.iterator(); modules.hasNext();)
			{
				AxisModule module = (AxisModule) modules.next();
				message += "<li><b>" + module.getName() + "</b>: "
						+ module.getModuleDescription() + "</li>";
			}

			message += "</ul></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);

		}
	}
}
