package axis2swing.ui.content;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.data.Module;
import axis2swing.ui.Axis2SwingController;

public class AvailableModulesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public AvailableModulesPanel(Axis2SwingController controller)
	{
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initGUI()
	{
		List<Module> lstModule = controller.getAvailableModules();

		if (lstModule == null || (lstModule != null && lstModule.isEmpty()))
		{
			setHeader("No available module");
		}
		else
		{
			setHeader("Available Modules");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			String message = "<html><ul>";

			for (int i = 0; i < lstModule.size(); i++)
			{
				Module module = lstModule.get(i);
				message += "<li><b>" + module.getName() + "</b>: "
						+ module.getDescription() + "</li>";
			}

			message += "</ul></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);

		}
	}
}
