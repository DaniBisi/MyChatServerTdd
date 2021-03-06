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
public class ClientHandlerTest extends TestCase {
	private HttpUser cmd;
	private InputStream in;
	private OutputStream out;
	
	@Mock
	private Socket client;
	@Mock
	private Database Db;	
	@Mock
	private FactoryHttpCommand factory;
	

	@InjectMocks
	private ClientHandler c1;
	

	@Before
	public void setUp() throws Exception {

//		Database Db = mock(Database.class);
//		client = mock(Socket.class);
		
		in = mock(InputStream.class);
		out = mock(OutputStream.class);
		cmd = mock(HttpUser.class);
//		when(client.getInputStream()).thenReturn(in);
//		when(client.getOutputStream()).thenReturn(out);
		//when(cmd.execute(c1)).thenReturn("OK\r\n");
		when(factory.getCmd("USER\r\n",0)).thenReturn(cmd);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	@Test
	public void testClientHandler(){
		assertEquals(client, c1.getSocket());
	}

	@Test
	public void testGetCommand() throws IOException{	
		when(in.read()).thenReturn(99, 105, 97, 111,13, 10, -1);
        MockitoAnnotations.initMocks(this);
		String response = c1.getAnsware();
		assertEquals("KO\r\n", response);
	}
	@Test
	public void testGetCommandEmpty() throws IOException{	
		when(in.read()).thenReturn(99, 105, 97, 111, -1);
        MockitoAnnotations.initMocks(this);
		String response = c1.getAnsware();
		assertEquals("KO\r\n", response);
	}

	@Test
	public void testExecuteNotValidCommand() throws IOException{
		when(in.read()).thenReturn(99 ,105, 97,111,13 ,10,-1);
        MockitoAnnotations.initMocks(this);
		String response = c1.getAnsware();
		assertEquals("KO\r\n", response);
	}
	@Test 
	public void testExecuteValidUserCommand() throws IOException{
		when(in.read()).thenReturn(85, 83, 69, 82 ,13,10,-1);
        MockitoAnnotations.initMocks(this);
		String response = c1.getAnsware();
		assertEquals("OK\r\n", response);
	}
	
	@Test 
	public void testUnexpectedEndOfStream() throws IOException{
		when(in.read()).thenReturn(85, -1);
        MockitoAnnotations.initMocks(this);
		String response = c1.getAnsware();
		assertEquals("KO\r\n", response);
	}
	@Test
	public void testSetLoginStatus(){
		c1.setLoginStatus(2);
		assertEquals(2, c1.getLoginStatus());
	}
	
	/*
	@Test
	public void testExecuteValidPassCommand() throws IOException{
		when(in.read()).thenReturn(80, 65, 83, 83 ,13,10,-1);
        MockitoAnnotations.initMocks(this);
        c1.setLoginStatus(1);
		String response = c1.getAnsware();
		assertEquals("OK\r\n", response);
	}
	@Test 
	public void testExecuteValidUser() throws IOException{
		//int[] msg= convert("ciao");
		when(in.read()).thenReturn(85, 83, 69, 82, 32, 100, 97, 110, 105,13,10,-1); //USER dani\r\r
        MockitoAnnotations.initMocks(this);
		String response = c1.getAnsware();
		assertEquals("dani", c1.userName);
		assertEquals("OK\r\n", response);
	}*/
	
	public int[] convert(String str) {
	    int[] result = new int[str.length()+3];
	    int i;
	    for (i = 0; i < str.length(); i++) {
	        result[i] = str.charAt(i);
	    }
	    result[1+1]=13;
	    result[1+2]=10;
	    result[1+3]=-1;
	    return result;
	}
}
