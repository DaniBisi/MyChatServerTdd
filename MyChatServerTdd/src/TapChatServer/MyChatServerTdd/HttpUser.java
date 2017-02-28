package TapChatServer.MyChatServerTdd;

public class HttpUser implements IHttpProtocol {

	public String execute(ClientHandler clientHandler) {
		clientHandler.setLoginStatus(1);
		return "OK\r\n";
	}

}
