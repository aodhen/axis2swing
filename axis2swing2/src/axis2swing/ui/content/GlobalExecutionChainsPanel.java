package axis2swing.ui.content;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.engine.Phase;


import axis2swing.ui.Axis2SwingUIController;

public class GlobalExecutionChainsPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public GlobalExecutionChainsPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		AxisConfiguration axisConfiguration = controller.getAxisConfiguration();

	
			setHeader("View Global Execution Chains");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			displayExecutionChains(axisConfiguration.getInFlowPhases(),
					"In Flow Up To and Including Dispatcher");
			displayExecutionChains(axisConfiguration.getInFaultFlowPhases(),
					"In Fault Flow");
			displayExecutionChains(axisConfiguration.getOutFlowPhases(), "Out Flow");
			displayExecutionChains(axisConfiguration.getOutFaultFlowPhases(),
					"Out Fault Flow");
		
	}

	private void displayExecutionChains(List<Phase> lstPhase, String title)
	{
		if (lstPhase != null && !lstPhase.isEmpty())
		{
			String message = "<html><h3>" + title + "</h3><ul>";

			for (int i = 0; i < lstPhase.size(); i++)
			{
				//System.out.println("i=" +i);
				Phase phase = lstPhase.get(i);

				message += "<li>Phase Name: " + phase.getName();

				List<Handler> lstHandler = phase.getHandlers();

				if (lstHandler != null && !lstHandler.isEmpty())
				{
					message += "<ul>";

					for (int j = 0; j < lstHandler.size(); j++)
					{
						//System.out.println("j=" + j);
						message += "<li>Handler Name: "
								+ lstHandler.get(j).getName() + "</li>";
					}

					message += "</ul>";
				}

				message += "</li>";
			}

			message += "</ul></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);
		}
	}
}
