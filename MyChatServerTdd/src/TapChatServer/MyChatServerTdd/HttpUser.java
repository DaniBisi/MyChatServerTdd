package TapChatServer.MyChatServerTdd;

public class HttpUser implements IHttpProtocol {

	private boolean error;
	private String userName;
	public HttpUser(String[] param){
		this.userName = param[0];
	}
	public String execute(ClientHandler clientHandler) {
		clientHandler.setLoginStatus(1);
		clientHandler.setUserName(this.userName);
		
		return "OK\r\n";
	}

}
