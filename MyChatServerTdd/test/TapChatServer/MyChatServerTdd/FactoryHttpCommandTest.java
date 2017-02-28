package TapChatServer.MyChatServerTdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FactoryHttpCommandTest {

	private FactoryHttpCommand f1;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		f1 = new FactoryHttpCommand();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCmdUser() {
		String cmd="USER\r\n";
		IHttpProtocol h1 = f1.getCmd(cmd, 0);
		assertEquals(true,h1 instanceof HttpUser);
	}
	@Test
	public void testGetCmdUserErrorStatus() {
		String cmd="USER\r\n";
		IHttpProtocol h1 = f1.getCmd(cmd, 1);
		assertEquals(true,h1 instanceof HttpError);
	}
	@Test
	public void testGetCmdUserSecondLoginStatus() {
		String cmd="USER\r\n";
		IHttpProtocol h1 = f1.getCmd(cmd, 2);
		assertEquals(true,h1 instanceof HttpUser);
	}
	@Test
	public void testGetCmdError() {
		String cmd="errorenelcomando\r\n";
		IHttpProtocol h1 = f1.getCmd(cmd, 0);
		assertEquals(true,h1 instanceof HttpError);
	}

}
