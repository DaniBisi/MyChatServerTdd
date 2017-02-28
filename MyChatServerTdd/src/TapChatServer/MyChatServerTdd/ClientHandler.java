package TapChatServer.MyChatServerTdd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler {

	private Socket client;
	private InputStream in;
	private OutputStream out;
	private FactoryHttpCommand factory;
	private int loginStatus;
	private Database db;

	public ClientHandler(Socket client,Database db) throws IOException{
		this.client = client;
		in = client.getInputStream();
		out = client.getOutputStream();
		this.factory = new FactoryHttpCommand();
		this.db = db;
		this.loginStatus = 0;
	}

	public Socket getSocket() {
		return this.client;
	}

	public String getAnsware() throws IOException {
		StringBuilder sb = new StringBuilder();
		String response ="KO\r\n";
		int byteRead;
		while((byteRead = in.read()) != -1){
			char ch = (char) byteRead;
			sb.append(String.valueOf(ch));
			int lenght = sb.length();
			String prov = "";
			if (lenght > 1){
				prov = sb.substring(lenght - 2, lenght);
			}
			if (prov.compareTo("\r\n") == 0) {
				response = execute(sb.toString());
				out.write(response.getBytes("latin1"));
				sb.setLength(0); // svuoto lo String builder
			}
		}
		return response;
	}

	public Database getDb(){
		return db;
		
	}
	private String execute(String string) {
		IHttpProtocol command = factory.getCmd(string, 0);
		String Result = command.execute(this);
		return Result;
	}
	
	public void setLoginStatus(int status){
		this.loginStatus = status;
	}

	public int getLoginStatus() {
		return this.loginStatus;
	}

}
