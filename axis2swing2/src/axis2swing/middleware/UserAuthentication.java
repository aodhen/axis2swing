package axis2swing.middleware;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class UserAuthentication{

	public static final int USER = 1;
	public static final int MANAGER = 2;
	public static final int ADMINISTRATOR = 3;
	
	public static int performAuthentication(String username, String password, String role){
    
	    int roleIndex = -1;
	    boolean isAuthenticated = false;
	    
	    try {
	
	            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	            Document doc = docBuilder.parse (new File("users.xml"));
	
	            // normalize text representation
	            doc.getDocumentElement ().normalize ();
	            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
	            // ROOT ELEMENT is of course "users"
	            
	            NodeList listOfRoles = doc.getElementsByTagName("role");
	            int totalRoles = listOfRoles.getLength();
	            System.out.println("Total no of roles : " + totalRoles);
	            
	            Element roleElement;
	            
	            for(int i=0; i<listOfRoles.getLength() ; i++){
	
	            	roleElement = (Element)listOfRoles.item(i);
	                //System.out.println(roleElement.getAttribute("name"));
	                
	                if (roleElement.getAttribute("name").equals(role)) {
	                	roleIndex = i;
	                	break;
	                }
	            }//end of for 1st for loop 
	            
	            if (roleIndex == -1) {
	            	System.err.println("No such role: " + role);
	            	System.exit(1);
	            }
	            
	            Node firstRoleNode = listOfRoles.item(roleIndex);
	            NodeList listOfUsers = firstRoleNode.getChildNodes();
	            
	            for(int j=0; j<listOfUsers.getLength() ; j++){
	            	Node firstUserNode = listOfUsers.item(j);
	            	if(firstUserNode.getNodeType() == Node.ELEMENT_NODE){
	            		Element firstUserElement = (Element)firstUserNode;
	            		
	            		NodeList usernameList = firstUserElement.getElementsByTagName("username");
	                    Element usernameElement = (Element)usernameList.item(0);
	                    NodeList textUsernameList = usernameElement.getChildNodes();
	                    String usernameVal = ((Node)textUsernameList.item(0)).getNodeValue().trim();
	                    
	                    // username must be unique
	                    if (usernameVal.equals(username)) {
	                    	NodeList passList = firstUserElement.getElementsByTagName("password");
	                    	Element passElement = (Element)passList.item(0);
	                    	NodeList textPassList = passElement.getChildNodes();
	                    	String passVal = ((Node)textPassList.item(0)).getNodeValue().trim();
	                    	
	                    	if (passVal.equals(password)) {
	                    		System.out.println("Authentication successful");
	                    		isAuthenticated = true;
	                    	}
	                    }
	
	            	}
	            } // end of 2nd for loop
	            
	            if (!isAuthenticated) {
	            	System.err.println("Authentication failed!");
	            	System.exit(1);
	            }
	
	        }catch (SAXParseException err) {
	        System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
	        System.out.println(" " + err.getMessage ());
	
	        }catch (SAXException e) {
	        Exception x = e.getException ();
	        ((x == null) ? e : x).printStackTrace ();
	
	        }catch (Throwable t) {
	        t.printStackTrace ();
	        }
	        //System.exit (0);
	    return roleIndex+1;
    }
        
    public static void main (String argv []){
        	
    	if (argv.length != 3) {
        	System.err.println("Usage: java TestXML <username> <password> <role>");
        	System.exit(1);
        }
       
    	String username = argv[0];
        String password = argv[1];
        String role = argv[2];
        
    	int result = performAuthentication(username, password, role);
    	
    	if (result == USER)
    		System.out.println("Is user");
    	else if (result == MANAGER)
    		System.out.println("Is manager");
    	if (result == ADMINISTRATOR)
    		System.out.println("Is administrator");
    
    }//end of main


}