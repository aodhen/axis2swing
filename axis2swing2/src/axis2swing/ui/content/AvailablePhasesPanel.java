package axis2swing.ui.content;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import org.apache.axis2.deployment.DeploymentException;
import org.apache.axis2.deployment.util.PhasesInfo;
import org.apache.axis2.engine.Phase;

import axis2swing.ui.Axis2SwingUIController;

public class AvailablePhasesPanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public AvailablePhasesPanel(Axis2SwingUIController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		PhasesInfo phasesInfo = controller.getPhaseInfo();
		
		if (phasesInfo != null)
		{
			setHeader("Available Phases");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
				displayPhaseInfo(phasesInfo);

				
		}
		else
		{
			setHeader("No available phases");
		}
	}

	private void displayPhaseInfo(PhasesInfo phasesInfo)
	{
		String message = "<html><h2><font color=\"blue\">" + "System Defined Phases"
				+ "</font></h2></html>";

		JLabel lblMessage = new JLabel(message);
		add(lblMessage);
		
		try {
			
			List<Phase> lstPhase = phasesInfo.getGlobalInflow();
			displayPhases(lstPhase, "InFlow Up to Dispatcher");
	
			lstPhase = phasesInfo.getGlobalInFaultPhases();
			displayPhases(lstPhase, "InFaultFlow");
	
			lstPhase = phasesInfo.getGlobalOutPhaseList();
			displayPhases(lstPhase, "OutFlow");

		
			lstPhase = phasesInfo.getOUT_FaultPhases();
			displayPhases(lstPhase, "OutFaultFlow");

		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		message = "<html><h2><font color=\"blue\">" + "User Defined Phases"
		+ "</font></h2></html>";

		lblMessage = new JLabel(message);
		add(lblMessage);
		
		try {
			
			List<Phase> lstPhase = phasesInfo.getOperationInPhases();
			displayPhases(lstPhase, "InFlow Up to Dispatcher");
		
			lstPhase = phasesInfo.getOperationInFaultPhases();
			displayPhases(lstPhase, "InFaultFlow");
		
			lstPhase = phasesInfo.getOperationOutPhases();
			displayPhases(lstPhase, "OutFlow");
		
		
			lstPhase = phasesInfo.getOperationOutFaultPhases();
			displayPhases(lstPhase, "OutFaultFlow");
		
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

	private void displayPhases(List<Phase> lstPhase, String title)
	{
		if (lstPhase != null && !lstPhase.isEmpty())
		{
			String message = "<html><b>" + title + "</b><blockquote>";

			for (int i = 0; i < lstPhase.size(); i++)
			{
				message += lstPhase.get(i).getPhaseName() + "<br>";
			}

			message += "</blockquote></html>";

			JLabel newLabel = new JLabel(message);
			add(newLabel);
		}
	}
}
