package axis2swing.ui;

import java.util.LinkedList;
import java.util.List;

import axis2swing.data.Handler;
import axis2swing.data.Module;
import axis2swing.data.Operation;
import axis2swing.data.Phase;
import axis2swing.data.PhaseInfo;
import axis2swing.data.Service;
import axis2swing.data.ServiceGroup;
import axis2swing.data.UserRole;

public class Axis2SwingUIController
{
	private String username;
	private UserRole userRole;

	public boolean login(String username, char[] password)
	{
		this.username = username;
		// TODO check username and password

		// TODO get the user role

		// TODO remove this stub
		setUserRole(UserRole.Admin);

		return true;
	}

	public boolean uploadService(String serviceFilePath)
	{
		// TODO upload service

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

	public List<Module> getAvailableModules()
	{
		List<Module> lstModule = new LinkedList<Module>();

		// TODO get available modules

		// TODO remove this stub
		lstModule.add(new Module("script"));
		lstModule.add(new Module("metadataExchange"));
		lstModule
				.add(new Module(
						"addressing",
						"This is the WS-Addressing implementation on Axis2, supporting the "
								+ "WS-Addressing 1.0 Recommendation, as well as the Submission version (2004/08)."));

		return lstModule;
	}

	public List<Module> getGloballyEngagedModules()
	{
		List<Module> lstModule = new LinkedList<Module>();

		// TODO get globally engaged modules

		// TODO remove this stub
		lstModule.add(new Module("script"));
		lstModule.add(new Module("metadataExchange"));
		lstModule
				.add(new Module(
						"addressing",
						"This is the WS-Addressing implementation on Axis2, supporting the "
								+ "WS-Addressing 1.0 Recommendation, as well as the Submission version (2004/08)."));

		return lstModule;
	}

	public PhaseInfo getSystemDefinedPhaseInfo()
	{
		PhaseInfo phaseInfo = new PhaseInfo();

		// TODO get system defined phase info

		// TODO remove this stub
		phaseInfo.addInFlowPhase(new Phase("Transport"));
		phaseInfo.addInFaultFlowPhase(new Phase("PreDispatch"));
		phaseInfo.addOutFlowPhase(new Phase("MessageOut"));
		phaseInfo.addOutFaultFlowPhase(new Phase("Security"));

		return phaseInfo;
	}

	public PhaseInfo getUserDefinedPhaseInfo()
	{
		PhaseInfo phaseInfo = new PhaseInfo();

		// TODO get user defined phase info

		// TODO remove this stub
		phaseInfo.addInFlowPhase(new Phase("RMPhase"));
		phaseInfo.addInFaultFlowPhase(new Phase("OperationInFaultPhase"));
		phaseInfo.addOutFlowPhase(new Phase("soapmonitorPhase"));
		phaseInfo.addOutFaultFlowPhase(new Phase("PolicyDetermination"));

		return phaseInfo;
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

	public List<Service> getAvailableServices()
	{
		List<Service> lstService = new LinkedList<Service>();

		// TODO get available services

		// TODO remove this stub
		Service newService = new Service("Version",
				"http://localhost:8080/axis2/services/Version", true);
		newService.addModule(new Module("addressing"));
		Operation newOperation = new Operation("getVersion");
		newOperation.addModule(new Module("addressing"));
		newService.addOperation(newOperation);
		lstService.add(newService);

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

	public List<ServiceGroup> getAvailableServiceGroups()
	{

		List<ServiceGroup> lstGroup = new LinkedList<ServiceGroup>();

		// TODO get available service group

		// TODO remove this stub
		{
			Service newService = new Service("Version",
					"http://localhost:8080/axis2/services/Version", true);
			newService.addOperation(new Operation("getVersion"));
			ServiceGroup newGroup = new ServiceGroup("version-1.4");
			newGroup.addService(newService);
			newGroup.addModule(new Module("addressing"));
			lstGroup.add(newGroup);
		}

		return lstGroup;
	}

	public List<Service> getActiveServices()
	{
		List<Service> lstService = new LinkedList<Service>();

		// TODO get active services

		// TODO remove this stub
		{
			lstService = getAvailableServices();
		}

		return lstService;
	}

	public List<Service> getInactiveServices()
	{
		List<Service> lstService = new LinkedList<Service>();

		// TODO get inactive services

		// TODO remove this stub
		{
			lstService = getAvailableServices();
		}

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
