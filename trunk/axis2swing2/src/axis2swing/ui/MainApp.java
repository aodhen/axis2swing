package axis2swing.ui;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.engine.AxisServer;
import org.apache.axis2.transport.http.SimpleHTTPServer;
import org.apache.axis2.util.CommandLineOption;
import org.apache.axis2.util.CommandLineOptionParser;
import org.apache.axis2.util.OptionsValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;

import axis2swing.middleware.Axis2AdminManager;

public class MainApp extends AxisServer {
	
	private static final Log log = LogFactory.getLog(SimpleHTTPServer.class);

    int port = -1;

    public static int DEFAULT_PORT = 8080;

    public MainApp (
            String repoLocation,
            String confLocation) throws Exception {
        super(false);
        configContext = ConfigurationContextFactory
                .createConfigurationContextFromFileSystem(repoLocation,
                        confLocation);
    }
    
    public static void main(String[] args) throws Exception {
        String repoLocation = "axis2-1.4/repository";
        String confLocation = "axis2-1.4/conf/axis2.xml";

        /*CommandLineOptionParser optionsParser = new CommandLineOptionParser(args);
        List invalidOptionsList = optionsParser.getInvalidOptions(new OptionsValidator() {
            public boolean isInvalid(CommandLineOption option) {
                String optionType = option.getOptionType();
                return !("repo".equalsIgnoreCase(optionType) || "conf"
                        .equalsIgnoreCase(optionType));
            }
        });

        if ((invalidOptionsList.size() > 0) || (args.length > 4)) {
            printUsage();
            return;
        }

        Map optionsMap = optionsParser.getAllOptions();

        CommandLineOption repoOption = (CommandLineOption) optionsMap
                .get("repo");
        CommandLineOption confOption = (CommandLineOption) optionsMap
                .get("conf");

        log.info("[SimpleAxisServer] Starting");
        if (repoOption != null) {
            repoLocation = repoOption.getOptionValue();
            log.info("[SimpleAxisServer] Using the Axis2 Repository"
                    + new File(repoLocation).getAbsolutePath());
            System.out.println("[SimpleAxisServer] Using the Axis2 Repository"
                    + new File(repoLocation).getAbsolutePath());
        }
        if (confOption != null) {
            confLocation = confOption.getOptionValue();
            System.out
                    .println("[SimpleAxisServer] Using the Axis2 Configuration File"
                            + new File(confLocation).getAbsolutePath());
        }*/

        try {
            MainApp server = new MainApp(repoLocation, confLocation);
            server.start();
            log.info("[MainApp] Started");
            System.out.println("[MainApp] Started");
            
            Axis2AdminManager manager = new Axis2AdminManager(server.getConfigurationContext());
            
            
             
            
                        		
        } catch (Throwable t) {
            log.fatal("[MainApp] Shutting down. Error starting MainApp", t);
            System.err.println("[MainApp] Shutting down. Error starting MainApp");
        }
    }

}
