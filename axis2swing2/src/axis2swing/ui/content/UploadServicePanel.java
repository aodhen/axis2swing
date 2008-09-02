package axis2swing.ui.content;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import axis2swing.ui.Axis2SwingUIController;

public class UploadServicePanel extends PanelContent implements MouseListener{

	private JButton btnBrowse;
	private JButton btnUpload;
	private JTextField txtFilePath;
	
	public UploadServicePanel(Axis2SwingUIController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadComponent() {		
		JLabel lblWelcome = new JLabel();
		this.add(lblWelcome);
		lblWelcome.setText("Upload an Axis Service Archive File");
		lblWelcome.setBounds(12, 12, 688, 25);
		lblWelcome.setFont(new java.awt.Font("Dialog",1,20));
		lblWelcome.setVerticalAlignment(SwingConstants.TOP);
		lblWelcome.setVerticalTextPosition(SwingConstants.TOP);
	
		JTextPane txtMessage = new JTextPane();
		this.add(txtMessage);
		txtMessage.setText("You can upload a packaged Axis2 service from this page in two small steps." +
				"\n\n# Browse to the location and select the axis service archive file you wish to upload" +
				"\n# Click \"Upload\" button" +
				"\n\nSimple as that!");
		txtMessage.setBounds(12, 49, 611, 94);
	
		JLabel lblUpload = new JLabel();
		this.add(lblUpload);
		lblUpload.setText("Service Archieve");
		lblUpload.setBounds(12, 155, 150, 15);
	
		txtFilePath = new JTextField();
		this.add(txtFilePath);
		txtFilePath.setBounds(122, 151, 395, 22);
	
		btnBrowse = new JButton("Browse...");
		this.add(btnBrowse);
		btnBrowse.addMouseListener(this);
		btnBrowse.setBounds(529, 151, 100, 22);
	
		btnUpload = new JButton();
		this.add(btnUpload);
		btnUpload.addMouseListener(this);
		btnUpload.setText("Upload");
		btnUpload.setBounds(122, 185, 100, 22);

	}

	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JButton) {
			JButton theButton = (JButton)me.getSource();
			
			if(theButton.equals(btnBrowse)) {
				//TODO browse for service
				JFileChooser fileChooser = new JFileChooser();
				
				int result = fileChooser.showOpenDialog(this);
				txtFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
			else if(theButton.equals(btnUpload)) {
				
				if(!controller.uploadService(txtFilePath.getText()))						
				{
					//TODO error uploading service
				}
			}		
		}
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}