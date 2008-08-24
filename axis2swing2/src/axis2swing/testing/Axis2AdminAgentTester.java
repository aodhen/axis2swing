package axis2swing.testing;

import axis2swing.middleware.Axis2AdminManager;
import junit.framework.TestCase;


public class Axis2AdminAgentTester extends TestCase {
	
	public void test_loading () {
		Axis2AdminManager manager = new Axis2AdminManager();
		assertNotNull(manager);
	}
}
