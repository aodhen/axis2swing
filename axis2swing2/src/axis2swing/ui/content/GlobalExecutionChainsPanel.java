package axis2swing.ui.content;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.data.Handler;
import axis2swing.data.Phase;
import axis2swing.data.PhaseInfo;
import axis2swing.ui.Axis2SwingController;

public class GlobalExecutionChainsPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public GlobalExecutionChainsPanel(Axis2SwingController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		PhaseInfo globalChains = controller.getGlobalExecutionChains();

		if (globalChains != null)
		{
			setHeader("View Global Execution Chains");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			displayExecutionChains(globalChains.getInFlowPhases(),
					"In Flow Up To and Including Dispatcher");
			displayExecutionChains(globalChains.getInFaultFlowPhases(),
					"In Fault Flow");
			displayExecutionChains(globalChains.getOutFlowPhases(), "Out Flow");
			displayExecutionChains(globalChains.getOutFaultFlowPhases(),
					"Out Fault Flow");
		}
		else
		{
			setHeader("No global execution chains");
		}
	}

	private void displayExecutionChains(List<Phase> lstPhase, String title)
	{
		if (lstPhase != null && !lstPhase.isEmpty())
		{
			String message = "<html><h3>" + title + "</h3><ul>";

			for (int i = 0; i < lstPhase.size(); i++)
			{
				Phase phase = lstPhase.get(i);

				message += "<li>Phase Name: " + phase.getName();

				List<Handler> lstHandler = phase.getHandlers();

				if (lstHandler != null && !lstHandler.isEmpty())
				{
					message += "<ul>";

					for (int j = 0; j < lstHandler.size(); j++)
					{
						message += "<li>Handler Name: "
								+ lstHandler.get(i).getName() + "</li>";
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
