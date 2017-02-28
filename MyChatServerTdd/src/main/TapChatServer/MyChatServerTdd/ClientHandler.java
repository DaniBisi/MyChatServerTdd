package main.TapChatServer.MyChatServerTdd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler {

	private Socket client;
	private InputStream in;
	private OutputStream out;

	public ClientHandler(Socket client) throws IOException{
		this.client = client;
		in = client.getInputStream();
		out = client.getOutputStream();
	}

	public Socket getSocket() {
		return this.client;
	}

	public String getCommand() throws IOException {
		StringBuilder sb = new StringBuilder();
		String response ="";
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

	private String execute(String string) {
		
		return "KO\r\n";
	}
	
}
