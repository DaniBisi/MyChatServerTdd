package test.TapChatServer.MyChatServerTdd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;

import org.mockito.Matchers;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;
import main.TapChatServer.MyChatServerTdd.ClientHandler;

public class ClientHandlerTest extends TestCase {

	private Socket client;
	private ClientHandler c1;
	private InputStream in;
	private OutputStream out;

	protected void setUp() throws Exception {

		client = mock(Socket.class);
		in = mock(InputStream.class);
		out = mock(OutputStream.class);
		when(client.getInputStream()).thenReturn(in);
		when(client.getOutputStream()).thenReturn(out);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testClientHandler(){
		assertEquals(client, c1.getSocket());
	}

	public void testGetCommand() throws IOException{	
		when(in.read()).thenReturn(99, 105, 97, 111,13, 10, -1);
		//when(out.write("KO\r\n".getBytes("latin1"))).thenReturn(null); //ciao
		c1 = new ClientHandler(client);
		String response = c1.getCommand();
		assertEquals("KO\r\n", response);
	}
	public void testGetCommandEmpty() throws IOException{	
		when(in.read()).thenReturn(99, 105, 97, 111, -1);
		//when(out.write("KO\r\n".getBytes("latin1"))).thenReturn(null); //ciao
		c1 = new ClientHandler(client);
		String response = c1.getCommand();
		assertEquals("", response);
	}
	
	public void testExecute() throws IOException{
		when(in.read()).thenReturn(85,83,69,82,13,10,-1);
		//when(out.write("KO\r\n".getBytes("latin1"))).thenReturn(null); //ciao
		c1 = new ClientHandler(client);
		String response = c1.getCommand();
		assertEquals("", response);
	}
	
}
