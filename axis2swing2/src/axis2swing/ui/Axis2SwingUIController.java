package axis2swing.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.axis2.deployment.util.PhasesInfo;

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

		// TODO disengage module from service

		return true;
	}

	public boolean disengageModuleFromOperation(String serviceName,
			String operationName, String moduleName)
	{

		// TODO disengage module from operation

		return true;
	}

	public Iterator getAvailableServiceGroups()
	{

		Iterator lstGroup = adminManager.getServiceGroups();

		return lstGroup;
	}

	public List<Service> getActiveServices()
	{
		List<Service> lstService = new LinkedList<Service>();

		// TODO get active services

	

		return lstService;
	}

	public List<Service> getInactiveServices()
	{
		List<Service> lstService = new LinkedList<Service>();

		// TODO get inactive services



		return lstService;
	}

	public boolean deactivateService(String serviceName)
	{
		// TODO deactivate service

		return true;
	}

	public boolean activateService(String serviceName)
	{
		// TODO activate service

		return true;
	}

	public boolean engageModuleGlobally(String moduleName)
	{
		// TODO engage module globally

		return true;
	}

	public boolean engageModuleGroup(String moduleName, String groupName)
	{
		// TODO engage module for service group

		return true;
	}

	public boolean engageModuleService(String moduleName, String serviceName)
	{
		// TODO engage module for service

		return true;
	}

	public Service getService(String serviceName)
	{

		// TODO get service based on service name

		// TODO remove this stub
		// {
		Service newService = new Service("Version",
				"http://localhost:8080/axis2/services/Version", true);
		newService.addModule(new Module("addressing"));

		Operation newOperation = new Operation("get Version");
		newService.addOperation(newOperation);

		PhaseInfo phaseInfo = getGlobalExecutionChains();
		newOperation.setPhaseInfo(phaseInfo);
		// }

		return newService;
	}

	public boolean engageModuleOperation(String serviceName, String moduleName,
			String operationName)
	{
		// TODO engage module for an operation

		return true;
	}
}
