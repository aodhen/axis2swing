package axis2swing.ui.content;

import java.awt.Component;

import javax.swing.JTextPane;

import axis2swing.ui.Axis2SwingUIController;

public class WelcomePanel extends PanelContent{

	public WelcomePanel(Axis2SwingUIController controller) {
		super(controller);
	}

	@Override
	public void loadComponent() {
		setHeader("Welcome to Axis2 Swing Admin Module!");
		
		JTextPane txtMessage = new JTextPane();
		this.add(txtMessage);
		txtMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtMessage.setText("You are now logged into the Axis2 administration console from inside the console you will be able to \n# to check on the health of your Axis2 deployment.\n# to change any parameters at run time.\n# to upload new services into Axis2 [Service hot-deployment].\"");
		txtMessage.setBounds(12, 49, 611, 94);
	}
	
}