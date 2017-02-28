package TapChatServer.MyChatServerTdd;

public class HttpPass implements IHttpProtocol {

	@Override
	public String execute(ClientHandler clientHandler) {
		clientHandler.setLoginStatus(2);
		return "OK\r\n";
	}

}
