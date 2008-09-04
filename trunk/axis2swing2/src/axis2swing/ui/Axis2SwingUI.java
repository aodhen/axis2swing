package axis2swing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import axis2swing.data.UserRole;
import axis2swing.ui.content.ActivateServicePanel;
import axis2swing.ui.content.AvailableModulesPanel;
import axis2swing.ui.content.AvailablePhasesPanel;
import axis2swing.ui.content.AvailableServiceGroupsPanel;
import axis2swing.ui.content.AvailableServicesPanel;
import axis2swing.ui.content.DeactivateServicePanel;
import axis2swing.ui.content.EngageModuleGloballyPanel;
import axis2swing.ui.content.GlobalExecutionChainsPanel;
import axis2swing.ui.content.GloballyEngagedModulesPanel;
import axis2swing.ui.content.PanelContent;
import axis2swing.ui.content.UploadServicePanel;
import axis2swing.ui.content.WelcomePanel;

public class Axis2SwingUI implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private Axis2SwingController controller;

	private JFrame frame;

	private PanelContent panContent;
	private JPanel panLogin;

	private JScrollPane scrollPane;

	public Axis2SwingUI()
	{
		controller = new Axis2SwingController();
		initGUI();
		frame.setVisible(true);
	}

	private void initGUI()
	{
		initFrame();
		initHeader();
		displayLoginPanel();
	}

	private void initFrame()
	{
		frame = new JFrame("Axis 2 Swing App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 750));
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void initHeader()
	{
		JPanel panHeader = new JPanel();
		panHeader.add(new JLabel(new ImageIcon("res/apache.gif")));
		panHeader.add(new JLabel(new ImageIcon("res/axis2.jpg")));
		panHeader.setBackground(Color.white);
		panHeader.setLayout(new FlowLayout());
		frame.getContentPane().add(panHeader, BorderLayout.PAGE_START);
	}

	private void displayLoginPanel()
	{
		panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setLayout(new GridBagLayout());
		frame.getContentPane().add(panLogin, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();

		JLabel lblUsername = new JLabel("Username:");
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		panLogin.add(lblUsername, gbc);

		final JTextField txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(150, 22));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panLogin.add(txtUsername, gbc);

		JLabel lblPassword = new JLabel("Password:");
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		panLogin.add(lblPassword, gbc);

		final JPasswordField txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(150, 22));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panLogin.add(txtPassword, gbc);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				txtUsername.setText("");
				txtPassword.setText("");
			}

		});
		btnClear.setActionCommand("clear");
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		panLogin.add(btnClear, gbc);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae)
			{
				if (controller.login(txtUsername.getText(), txtPassword
						.getPassword()))
				{
					displayWelcomePanel();
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
							"Invalid username and password.", "Login error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnLogin.setActionCommand("login");
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		panLogin.add(btnLogin, gbc);

	}

	private void displayWelcomePanel()
	{
		panLogin.setVisible(false);
		frame.getContentPane().remove(panLogin);

		initMenu();

		panContent = new WelcomePanel(controller);
		scrollPane = new JScrollPane(panContent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBorder(null);
		// frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(panContent, BorderLayout.CENTER);
	}

	private void initMenu()
	{

		JPanel panMenu = new JPanel();
		panMenu.setBackground(Color.white);
		panMenu.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
		panMenu.setLayout(new BoxLayout(panMenu, BoxLayout.PAGE_AXIS));

		JScrollPane scrollMenu = new JScrollPane(panMenu,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollMenu.setBorder(null);
		scrollMenu.setPreferredSize(new Dimension(250, 300));

		frame.getContentPane().add(scrollMenu, BorderLayout.LINE_START);

		JPanel panSubmenu;
		if (controller.getUserRole() == UserRole.Admin)
		{
			panSubmenu = createButtonGroup(panMenu, "Tools");
			createButton(panSubmenu, "Upload Service", "uploadService");
		}

		panSubmenu = createButtonGroup(panMenu, "System Components");
		createButton(panSubmenu, "Available Services", "viewServices");
		createButton(panSubmenu, "Available Service Groups", "viewGroups");
		createButton(panSubmenu, "Available Modules", "viewModules");
		createButton(panSubmenu, "Globally Engaged Modules",
				"viewGlobalModules");
		createButton(panSubmenu, "Available Phases", "viewPhases");

		panSubmenu = createButtonGroup(panMenu, "Execution Chains");
		createButton(panSubmenu, "Global Chains", "viewGlobalChains");
		createButton(panSubmenu, "Operation Specific Chains",
				"viewOperationChains");

		if (controller.getUserRole() != UserRole.User)
		{
			panSubmenu = createButtonGroup(panMenu, "Engage Module");
			createButton(panSubmenu, "For All Services", "engageModuleGlobal");
			createButton(panSubmenu, "For a Service Group", "engageModuleGroup");
			createButton(panSubmenu, "For a Service", "engageModuleService");
			createButton(panSubmenu, "For an Operation",
					"engageModuleOperation");

			panSubmenu = createButtonGroup(panMenu, "Services");
			createButton(panSubmenu, "Deactivate Service", "deactivateService");
			createButton(panSubmenu, "Activate Service", "activateService");
			createButton(panSubmenu, "Edit Parameters", "editParams");
		}

		panSubmenu = createButtonGroup(panMenu, "Contexts");
		createButton(panSubmenu, "View Hierarchy", "viewHierarchy");
	}

	private JPanel createButtonGroup(JPanel panMenu, String text)
	{
		JPanel panButton = new JPanel();
		panButton.setBackground(Color.white);
		panButton.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.black), text));
		panButton.setLayout(new BoxLayout(panButton, BoxLayout.PAGE_AXIS));
		panMenu.add(panButton);
		return panButton;
	}

	private void createButton(JPanel panButton, String text,
			String actionCommand)
	{
		JButton newButton = new JButton(text);
		newButton.addActionListener(this);
		newButton.setActionCommand(actionCommand);
		panButton.add(newButton);
		panButton.add(new Box.Filler(new Dimension(Short.MIN_VALUE, 0),
				new Dimension(10, 0), new Dimension(Short.MAX_VALUE, 0)));
	}

	private void displayNewContent(PanelContent newPanelContent)
	{
		panContent.setVisible(false);
		frame.getContentPane().remove(panContent);

		panContent = newPanelContent;
		frame.getContentPane().add(panContent, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("uploadService"))
		{
			displayNewContent(new UploadServicePanel(controller));
		}
		else if (ae.getActionCommand().equals("viewServices"))
		{
			displayNewContent(new AvailableServicesPanel(controller));
		}
		else if (ae.getActionCommand().equals("viewGroups"))
		{
			displayNewContent(new AvailableServiceGroupsPanel(controller));
		}
		else if (ae.getActionCommand().equals("viewModules"))
		{
			displayNewContent(new AvailableModulesPanel(controller));
		}
		else if (ae.getActionCommand().equals("viewGlobalModules"))
		{
			displayNewContent(new GloballyEngagedModulesPanel(controller));
		}
		else if (ae.getActionCommand().equals("viewPhases"))
		{
			displayNewContent(new AvailablePhasesPanel(controller));
		}
		else if (ae.getActionCommand().equals("viewGlobalChains"))
		{
			displayNewContent(new GlobalExecutionChainsPanel(controller));
		}
		else if (ae.getActionCommand().equals("viewOperationChains"))
		{

		}
		else if (ae.getActionCommand().equals("engageModuleGlobal"))
		{
			displayNewContent(new EngageModuleGloballyPanel(controller));
		}
		else if (ae.getActionCommand().equals("engageModuleGroup"))
		{

		}
		else if (ae.getActionCommand().equals("engageModuleService"))
		{

		}
		else if (ae.getActionCommand().equals("engageModuleOperation"))
		{

		}
		else if (ae.getActionCommand().equals("deactivateService"))
		{
			displayNewContent(new DeactivateServicePanel(controller));
		}
		else if (ae.getActionCommand().equals("activateService"))
		{
			displayNewContent(new ActivateServicePanel(controller));
		}
		else if (ae.getActionCommand().equals("editParams"))
		{

		}
		else if (ae.getActionCommand().equals("viewHierarchy"))
		{

		}
	}
}