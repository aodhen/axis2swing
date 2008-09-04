package axis2swing.ui.content;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import axis2swing.ui.Axis2SwingController;

public class WelcomePanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public WelcomePanel(Axis2SwingController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Welcome to Axis2 Swing Admin Module");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String welcomeMsg = "<html><p>You are now logged into the Axis2 administration console from inside the console you will be able to</p>"
				+ "<ul>"
				+ "<li>to check on the health of your Axis2 deployment.</li>"
				+ "<li>to change any parameters at run time.</li>"
				+ "<li>to upload new services into Axis2 [Service hot-deployment].</li>"
				+ "</ul>" + "</html>";

		JLabel lblWelcomeMsg = new JLabel();
		lblWelcomeMsg.setText(welcomeMsg);
		add(lblWelcomeMsg);
	}
}
