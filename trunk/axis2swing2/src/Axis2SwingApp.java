import javax.swing.UIManager;

import axis2swing.ui.Axis2SwingUI;


public class Axis2SwingApp {
	public static void main(String[] args) {
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			//do nothing
		}
		
		new Axis2SwingUI();
	}
}