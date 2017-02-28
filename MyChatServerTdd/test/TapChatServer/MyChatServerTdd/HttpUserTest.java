package TapChatServer.MyChatServerTdd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class HttpUserTest extends TestCase {
	

	private ClientHandler c1;
	private HttpUser h1;
	

	@Before
	public void setUp() throws Exception {
		c1 = mock(ClientHandler.class);
	}
	@Test
	public void testExecute(){
		h1 = new HttpUser();
		h1.execute(c1);
		verify(c1).setLoginStatus(1);
	}
	
}
