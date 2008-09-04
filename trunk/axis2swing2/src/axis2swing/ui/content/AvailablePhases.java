package axis2swing.ui.content;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.data.Phase;
import axis2swing.data.PhaseInfo;
import axis2swing.ui.Axis2SwingController;

public class AvailablePhasesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public AvailablePhasesPanel(Axis2SwingController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		PhaseInfo systemDefined = controller.getSystemDefinedPhaseInfo();
		PhaseInfo userDefined = controller.getUserDefinedPhaseInfo();

		if (systemDefined != null || userDefined != null)
		{
			setHeader("Available Phases");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			if (systemDefined != null)
				displayPhaseInfo(systemDefined, "System Defined Phases");

			if (userDefined != null)
				displayPhaseInfo(userDefined, "User Defined Phases");

		}
		else
		{
			setHeader("No available phases");
		}
	}

	private void displayPhaseInfo(PhaseInfo phaseInfo, String title)
	{
		String message = "<html><h2><font color=\"blue\">" + title
				+ "</font></h2></html>";

		JLabel lblMessage = new JLabel(message);
		add(lblMessage);

		List<Phase> lstPhase = phaseInfo.getInFlowPhases();
		displayPhases(lstPhase, "InFlow Up to Dispatcher");

		lstPhase = phaseInfo.getInFaultFlowPhases();
		displayPhases(lstPhase, "InFaultFlow");

		lstPhase = phaseInfo.getOutFlowPhases();
		displayPhases(lstPhase, "OutFlow");

		lstPhase = phaseInfo.getOutFaultFlowPhases();
		displayPhases(lstPhase, "OutFaultFlow");
	}

	private void displayPhases(List<Phase> lstPhase, String title)
	{
		if (lstPhase != null && !lstPhase.isEmpty())
		{
			String message = "<html><b>" + title + "</b><blockquote>";

			for (int i = 0; i < lstPhase.size(); i++)
			{
				message += lstPhase.get(i).getName() + "<br>";
			}

			message += "</blockquote></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);
		}
	}
}
