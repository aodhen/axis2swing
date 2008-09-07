import javax.swing.UIManager;

import axis2swing.ui.Axis2SwingUI;

public class Axis2SwingApp
{
	public static void main(String[] args)
	{
		try
		{
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e)
		{
			// do nothing
		}
		new Axis2SwingUI();
	}
}