package axis2swing.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import axis2swing.ui.Axis2SwingController;

public abstract class PanelContent extends JPanel
{
	private static final long serialVersionUID = 1L;

	protected Axis2SwingController controller;

	public PanelContent(Axis2SwingController controller)
	{
		this.controller = controller;
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(null);
		initGUI();
	}

	protected abstract void initGUI();

	protected void setHeader(String text)
	{
		JLabel lblHeader = new JLabel(text);
		lblHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
		Font font = lblHeader.getFont();
		font = new Font(font.getName(), Font.BOLD, 20);
		lblHeader.setFont(font);
		add(lblHeader, BorderLayout.PAGE_START);
	}
}
