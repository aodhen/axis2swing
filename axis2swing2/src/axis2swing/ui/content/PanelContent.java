package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import axis2swing.ui.Axis2SwingUIController;

public abstract class PanelContent extends JPanel {

	protected static final long serialVersionUID = 1L;
	
	protected Axis2SwingUIController controller;
	
	public PanelContent(Axis2SwingUIController controller) {
		this.controller = controller;
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		this.setLayout(null);
		loadComponent();
	}
	
	protected abstract void loadComponent();
	
	protected void setHeader(String text) {
		JLabel lblHeader = new JLabel();
		this.add(lblHeader);
		lblHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblHeader.setText(text);
		lblHeader.setBounds(12, 12, 688, 25);
		lblHeader.setFont(new java.awt.Font("Dialog",1,20));
		lblHeader.setVerticalAlignment(SwingConstants.TOP);
		lblHeader.setVerticalTextPosition(SwingConstants.TOP);
	}
}