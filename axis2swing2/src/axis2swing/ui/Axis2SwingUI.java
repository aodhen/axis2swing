package axis2swing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import axis2swing.ui.content.ActivateServicePanel;
import axis2swing.ui.content.AvailableModulesPanel;
import axis2swing.ui.content.AvailableServiceGroupsPanel;
import axis2swing.ui.content.AvailableServicesPanel;
import axis2swing.ui.content.DeactivateServicePanel;
import axis2swing.ui.content.EngageModuleForGroupPanel;
import axis2swing.ui.content.EngageModuleForServicePanel;
import axis2swing.ui.content.EngageModuleGloballyPanel;
import axis2swing.ui.content.GloballyEngagedModulePanel;
import axis2swing.ui.content.PanelContent;
import axis2swing.ui.content.UploadServicePanel;
import axis2swing.ui.content.WelcomePanel;


public class Axis2SwingUI implements MouseListener{
	
	private Axis2SwingUIController controller;
	
	private JButton btnClear;
	private JButton btnLogin;
	
	private JButton btnToolUploadService;

	private JButton btnSysComAvServ;
	private JButton btnSysComAvServGroups;
	private JButton btnSysComAvMod;
	private JButton btnSysComGloEngMod;
	private JButton btnSysComAvPhases;
	
	private JButton btnExeChainGlobal;
	private JButton btnExeChainSpecificOp;
	
	private JButton btnEngModAll;
	private JButton btnEngModGroup;
	private JButton btnEngModServ;
	private JButton btnEngModOp;
	
	private JButton btnDeactivateServ;
	private JButton btnActivateServ;
	private JButton btnEditParam;
	
	private JButton btnViewHierarchy;
	
	private JFrame frame;
	
	private JLabel lblErrorLogin;
	
	private JPanel panHeader;
	private JPanel panLogin;
	private JPanel panMenu;
	
	private JPasswordField txtPassword;
	
	private JTextField txtUsername;
	
	private PanelContent panContent;
	
	/**
	 * Creates a new object of type Axis2SwingUI.
	 */
	public Axis2SwingUI() 
	{
		controller = new Axis2SwingUIController();
		initialize();
		frame.setVisible(true);
	}
	
	private void displayMenu() {
		
		panMenu = new JPanel();
		panMenu.setBackground(Color.white);
		panMenu.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
		panMenu.setLayout(new BoxLayout(panMenu, BoxLayout.Y_AXIS));
		panMenu.setPreferredSize(new Dimension(250, panMenu.getHeight()));
		frame.getContentPane().add(panMenu, BorderLayout.WEST);
		
		//get role
		controller.getRole();
		
		//the buttons
		//TODO based on roles
		
		createMenuGroup("Tools");
		btnToolUploadService = new JButton("Upload Service");
		createMenuButton(btnToolUploadService);
		
		createMenuGroup("System Components");
		btnSysComAvServ = new JButton("Available Services");
		createMenuButton(btnSysComAvServ);
		btnSysComAvServGroups = new JButton("Available Service Groups");
		createMenuButton(btnSysComAvServGroups);
		btnSysComAvMod = new JButton("Available Modules");
		createMenuButton(btnSysComAvMod);
		btnSysComGloEngMod = new JButton("Globally Engaged Modules");
		createMenuButton(btnSysComGloEngMod);
		btnSysComAvPhases = new JButton("Available Phases");
		createMenuButton(btnSysComAvPhases);
		
		createMenuGroup("Execution Chains");
		btnExeChainGlobal = new JButton("Global Chains");
		createMenuButton(btnExeChainGlobal);
		btnExeChainSpecificOp = new JButton("Operation Specific Chaina");
		createMenuButton(btnExeChainSpecificOp);
		
		createMenuGroup("Engage Module");
		btnEngModAll = new JButton("For all Services");
		createMenuButton(btnEngModAll);
		btnEngModGroup = new JButton("For a Service Group");
		createMenuButton(btnEngModGroup);
		btnEngModServ = new JButton("For a Service");
		createMenuButton(btnEngModServ);
		btnEngModOp = new JButton("For an Operation");
		createMenuButton(btnEngModOp);
		
		createMenuGroup("Services");
		btnDeactivateServ = new JButton("Deactivate Service");
		createMenuButton(btnDeactivateServ);
		btnActivateServ = new JButton("Activate Service");
		createMenuButton(btnActivateServ);
		btnEditParam = new JButton("Edit Parameters");
		createMenuButton(btnEditParam);
		
		createMenuGroup("Contexts");
		btnViewHierarchy = new JButton("View Hierarchy");
		createMenuButton(btnViewHierarchy);
		
		
	}

	private void createMenuButton(JButton theButton) {
theButton.addMouseListener(this);
		theButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panMenu.add(theButton);
	}
	
	private void createMenuGroup(String text) {
		JLabel newLabel = new JLabel(text);
		newLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panMenu.add(newLabel);
	}
	
	private void displayMenuForAdmin() {
		
	}

	private void displayNewContent(PanelContent newContent) {
		frame.getContentPane().setVisible(false);
		frame.getContentPane().remove(panContent);
		
		panContent = newContent;
		frame.getContentPane().add(newContent, BorderLayout.CENTER);
		frame.getContentPane().setVisible(true);
	}
	
	/**
	 * Initializes the contents of the UI.
	 */
	private void initialize()
	{
		frame = new JFrame("Axis2Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 750));
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		
		//Content Panel
//		panContent = new JPanel();
//		panContent.setBackground(Color.white);
//		panContent.setBounds(frame.getBounds());
//		panContent.setLayout(new BorderLayout());
//		frame.getContentPane().add(panContent);
		
		
		//Header
		panHeader = new JPanel();
		panHeader.setBackground(Color.white);
		panHeader.add(new JLabel(new ImageIcon("res/apache.gif")));
		panHeader.add(new JLabel(new ImageIcon("res/axis2.jpg")));
		frame.getContentPane().add(panHeader, BorderLayout.NORTH);
		
		//Login Panel
		panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setLayout(new GridBagLayout());
		frame.getContentPane().add(panLogin, BorderLayout.CENTER);
		
		//Grid Bag Constraint
		GridBagConstraints gbc = new GridBagConstraints();

		//Username Label
		JLabel lblUsername = new JLabel("Username:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panLogin.add(lblUsername, gbc);
		
		//Username Textbox
		txtUsername = new JTextField();
		txtUsername.setSize(200, txtUsername.getSize().width);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panLogin.add(txtUsername, gbc);
		
		//Password Label
		JLabel lblPassword = new JLabel("Password:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panLogin.add(lblPassword, gbc);
		
		//Password Textbox
		txtPassword = new JPasswordField();
		txtPassword.setSize(200, txtPassword.getSize().height);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panLogin.add(txtPassword, gbc);
		
		//Clear Button
		btnClear = new JButton("Clear");
		btnClear.addMouseListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panLogin.add(btnClear, gbc);
		
		//Login Button
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(this);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panLogin.add(btnLogin, gbc);
		
		//Error Login Label
		lblErrorLogin = new JLabel("Invalid auth credentials!");
		lblErrorLogin.setForeground(Color.red);
		lblErrorLogin.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panLogin.add(lblErrorLogin, gbc);
	}

	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JButton) {
			JButton theButton = (JButton)me.getSource();
			
			if(theButton.equals(btnClear))
			{
				txtUsername.setText("");
				txtPassword.setText("");
			}
			else if(theButton.equals(btnLogin)) {
				if(controller.login(txtUsername.getText(), txtPassword.getPassword())) {
					//TODO: go to welcome page
					lblErrorLogin.setVisible(false);
					frame.getContentPane().setVisible(false);
					frame.getContentPane().remove(panLogin);

					displayMenu();
					
					panContent = new WelcomePanel(controller);
					frame.getContentPane().add(panContent, BorderLayout.CENTER);
					frame.getContentPane().setVisible(true);
				}
				else
				{
					lblErrorLogin.setVisible(true);
				}
			}
			else if(theButton.equals(btnToolUploadService))
			{
				displayNewContent(new UploadServicePanel(controller));
			}
			else if(theButton.equals(btnSysComAvServ))
			{
				displayNewContent(new AvailableServicesPanel(controller));
			}
			else if(theButton.equals(btnSysComAvServGroups)) {
				displayNewContent(new AvailableServiceGroupsPanel(controller));
			}
			else if(theButton.equals(btnSysComAvMod)) {
				displayNewContent(new AvailableModulesPanel(controller));
			}
			else if(theButton.equals(btnSysComGloEngMod)) {
				displayNewContent(new GloballyEngagedModulePanel(controller));
			}
			else if(theButton.equals(btnSysComAvPhases)) {
				//TODO display available phases
			}
			else if(theButton.equals(btnExeChainGlobal)) {
				//TODO display global chains
			}
			else if(theButton.equals(btnExeChainSpecificOp)) {
				//TODO display specific operation chains
			}
			else if(theButton.equals(btnEngModAll)) {
				displayNewContent(new EngageModuleGloballyPanel(controller));
			}
			else if(theButton.equals(btnEngModGroup)) {
				displayNewContent(new EngageModuleForGroupPanel(controller));
			}
			else if(theButton.equals(btnEngModServ)) {
				displayNewContent(new EngageModuleForServicePanel(controller));
			}
			else if(theButton.equals(btnEngModOp)) {
				//TODO display engage module for an operation
			}
			else if(theButton.equals(btnDeactivateServ)) {
				displayNewContent(new DeactivateServicePanel(controller));
			}
			else if(theButton.equals(btnActivateServ)) {
				displayNewContent(new ActivateServicePanel(controller));
			}
			else if(theButton.equals(btnEditParam)) {
				//TODO display edit parameters
			}
			else if(theButton.equals(btnViewHierarchy)) {
				//TODO display view hierarchy
			}
		}
	}

	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}
}