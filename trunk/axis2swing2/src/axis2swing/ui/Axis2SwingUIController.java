package axis2swing.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.axis2.deployment.util.PhasesInfo;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.AxisConfiguration;

import axis2swing.data.Handler;
import axis2swing.data.Module;
import axis2swing.data.Operation;
import axis2swing.data.Phase;
import axis2swing.data.PhaseInfo;
import axis2swing.data.Service;
import axis2swing.data.ServiceGroup;
import axis2swing.data.UserRole;
import axis2swing.middleware.Axis2AdminManager;
import axis2swing.middleware.UserAuthentication;

public class Axis2SwingUIController
{
	private String username;
	private UserRole userRole;
	private Axis2AdminManager adminManager;

	public void setAdminManger(Axis2AdminManager adminManger) {
		this.adminManager = adminManger;
	}

	public boolean login(String username, char[] password, String role)
	{
		this.username = username;
		
		// TODO check username and password
		String pass = "";
		for (int i=0; i < password.length; i++) {
			pass = pass + Character.toString(password[i]);
		}
		
		// TODO get the user role
		//System.out.println (username + pass + role);
		int roleIndex = UserAuthentication.performAuthentication(username, pass, role);
		//System.out.println("roleIndex" + roleIndex);
		if(roleIndex > -1) {
			if(roleIndex == UserAuthentication.USER) {
				setUserRole(UserRole.User);
			}
			else if (roleIndex == UserAuthentication.MANAGER) {
				setUserRole(UserRole.Manager);
			}
			else if (roleIndex == UserAuthentication.ADMINISTRATOR){
				setUserRole(UserRole.Admin);
			}
			return true;
		}
		// TODO remove this stub
		//setUserRole(role);

		return false;
	}

	public boolean uploadService(String serviceFilePath)
	{
		adminManager.processUploadFile(serviceFilePath);
		
		return true;
	}

	public void setUserRole(UserRole userRole)
	{
		this.userRole = userRole;
	}

	public UserRole getUserRole()
	{
		return userRole;
	}

	public Collection getAvailableModules()
	{
		Collection lstModule = adminManager.getModules();
		return lstModule;
	}

	public Collection getGloballyEngagedModules()
	{
		Collection lstModule = adminManager.getGlobalModules();
		return lstModule;
	}

	public PhasesInfo getPhaseInfo()
	{
		return adminManager.getPhases();
	}

	public PhaseInfo getGlobalExecutionChains()
	{
		PhaseInfo phaseInfo = new PhaseInfo();

		// TODO get global execution chains

		// TODO remove this stub
		Phase newPhase = new Phase("Transport");
		newPhase.addHandler(new Handler("RequestURIBasedDispatcher"));
		newPhase.addHandler(new Handler("SOAPActionBasedDispatcher"));
		phaseInfo.addInFlowPhase(newPhase);

		phaseInfo.addInFaultFlowPhase(new Phase("PreDispatch"));

		newPhase = new Phase("MessageOut");
		newPhase.addHandler(new Handler("AddressingOutHandler"));
		phaseInfo.addOutFlowPhase(newPhase);

		phaseInfo.addOutFaultFlowPhase(new Phase("Security"));

		return phaseInfo;
	}

	public Collection getAvailableServices()
	{
		Collection lstService = adminManager.getAxisServices();

		return lstService;
	}

	public boolean disengageModuleFromService(String serviceName,
			String moduleName)
	{

		adminManager.processDisengageModuleService(serviceName, moduleName);

		return true;
	}

	public boolean disengageModuleFromOperation(String serviceName,
			String operationName, String moduleName)
	{

		adminManager.processDisengageModuleOperation(serviceName, operationName, moduleName);

		return true;
	}

	public Iterator getAvailableServiceGroups()
	{

		Iterator lstGroup = adminManager.getServiceGroups();

		return lstGroup;
	}

	public ArrayList<AxisService> getActiveServices()
	{
		Collection lstService = adminManager.getAxisServices();
		ArrayList<AxisService> activeServices = new ArrayList<AxisService>();
		
		for(Iterator iterator = lstService.iterator(); iterator.hasNext();) {
			AxisService service = (AxisService) iterator.next();
			if(service.isActive()) {
				activeServices.add(service);
			}
		}
	
		return activeServices;
	}

	public ArrayList<AxisService> getInactiveServices()
	{
		Collection lstService = adminManager.getAxisServices();
		ArrayList<AxisService> inactiveServices = new ArrayList<AxisService>();
		
		for(Iterator iterator = lstService.iterator(); iterator.hasNext();) {
			AxisService service = (AxisService) iterator.next();
			if(!service.isActive()) {
				inactiveServices.add(service);
			}
		}
	
		return inactiveServices;	
	}

	public boolean deactivateService(String serviceName)
	{
		adminManager.processDeactiveService(serviceName);

		return true;
	}

	public boolean activateService(String serviceName)
	{
		adminManager.processActiveService(serviceName);

		return true;
	}

	public boolean engageModuleGlobally(String moduleName)
	{
		adminManager.processEngageGlobalModule(moduleName);

		return true;
	}
	
	public AxisConfiguration getAxisConfiguration() {
		return adminManager.getAxisConfiguration();
	}
	
	public boolean engageModuleGroup(String moduleName, String groupName)
	{
		adminManager.processEngageModuleServiceGroup(groupName, moduleName);

		return true;
	}

	public boolean engageModuleService(String moduleName, String serviceName)
	{
		adminManager.processEngageModuleService(serviceName, moduleName);

		return true;
	}

	public AxisService getService(String serviceName)
	{

		return adminManager.getAxisService(serviceName);
	}

	public boolean engageModuleOperation(String serviceName, String moduleName,
			String operationName)
	{
		adminManager.processEngageModuleOperation(serviceName, operationName, moduleName);

		return true;
	}
}
