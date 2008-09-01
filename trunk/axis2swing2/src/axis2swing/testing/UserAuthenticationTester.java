package axis2swing.testing;

import junit.framework.TestCase;
import axis2swing.middleware.UserAuthentication;

public class UserAuthenticationTester extends TestCase{
	
	public void test_authentication() {
		assertEquals(1, UserAuthentication.performAuthentication("admin", "adminpass", "administrator"));
		assertEquals(2, UserAuthentication.performAuthentication("manager", "mgrpass", "manager"));
		assertEquals(3, UserAuthentication.performAuthentication("user", "userpass", "user"));
		assertEquals(-1, UserAuthentication.performAuthentication("user", "userpass1", "user2"));
		assertEquals(-1, UserAuthentication.performAuthentication("user", "userpass1", "user"));
	}
	
}
