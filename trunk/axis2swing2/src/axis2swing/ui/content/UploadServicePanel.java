package axis2swing.ui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import axis2swing.ui.Axis2SwingController;

public class UploadServicePanel extends PanelContent
{
	private static final long serialVersionUID = 1L;

	public UploadServicePanel(Axis2SwingController controller)
	{
		super(controller);
	}

	@Override
	protected void initGUI()
	{
		setHeader("Upload an Axis Service Archive File");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		String message = "<html><p>You can upload a packaged Axis2 service from this page in two small steps.</p>"
				+ "<ul>"
				+ "<li>Browse to the location and select the axis service archive file you wish to upload</li>"
				+ "<li>Click \"Upload\" button</li>"
				+ "</ul>"
				+ "<p>Simple as that!</p></html>";

		JLabel newLabel = new JLabel();
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		newLabel.setText(message);
		add(newLabel);

		add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel panUpload = new JPanel();
		panUpload.setAlignmentX(Component.LEFT_ALIGNMENT);
		panUpload.setBackground(Color.white);
		panUpload.setLayout(new FlowLayout(FlowLayout.LEADING));
		panUpload.setMaximumSize(new Dimension(1000, 50));
		add(panUpload);

		newLabel = new JLabel();
		newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		newLabel.setText("Service Archieve");
		panUpload.add(newLabel);

		final JTextField txtFilePath = new JTextField();
		txtFilePath.setPreferredSize(new Dimension(350, 22));
		panUpload.add(txtFilePath);

		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae)
			{
				FileFilter filter = new ServiceFileExtensionFilter();

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(btnBrowse);

				if (returnValue == JFileChooser.APPROVE_OPTION)
				{
					txtFilePath.setText(fileChooser.getSelectedFile()
							.getAbsolutePath());
				}
			}

		});
		panUpload.add(btnBrowse);

		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae)
			{
				if (controller.uploadService(txtFilePath.getText()))
				{
					JOptionPane.showMessageDialog(null,
							"File successfully uploaded", "Upload Service",
							JOptionPane.INFORMATION_MESSAGE);
					txtFilePath.setText("");
				}
			}

		});
		add(btnUpload);

		message = "<html><blockquote>"
				+ "<p>Hot deployment of new service archives is enabled</p>"
				+ "<p>Hot update of existing service archives is disabled</p>"
				+ "</blockquote></html>";
		newLabel = new JLabel(message);
		add(newLabel);
	}
}

class ServiceFileExtensionFilter extends FileFilter
{

	@Override
	public boolean accept(File f)
	{
		if (f.getName().endsWith(".aar"))
			return true;

		return false;
	}

	@Override
	public String getDescription()
	{
		return "Axis2 Service File (.aar)";
	}

}
