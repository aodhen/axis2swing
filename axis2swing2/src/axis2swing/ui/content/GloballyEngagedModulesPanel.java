package axis2swing.ui.content;

import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import org.apache.axis2.description.AxisModule;

import java.util.Iterator;

import axis2swing.ui.Axis2SwingUIController;

public class GloballyEngagedModulesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public GloballyEngagedModulesPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		Collection lstModule = controller.getGloballyEngagedModules();

		if (lstModule == null || (lstModule != null && lstModule.isEmpty()))
		{
			setHeader("No globally engaged module");
		}
		else
		{
			setHeader("Globally Engaged Modules");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			String message = "<html><ul>";

			for (Iterator modules = lstModule.iterator(); modules.hasNext();)
			{
				AxisModule module = (AxisModule) modules.next();
				message += "<li>" + module.getName() + "</li>";
			}

			message += "</ul></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);

		}
	}
}
