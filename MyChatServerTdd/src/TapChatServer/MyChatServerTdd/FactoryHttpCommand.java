package TapChatServer.MyChatServerTdd;

public class FactoryHttpCommand {

	public IHttpProtocol getCmd(String string, int loginStatus) {
		String[] command = string.split("\r\n");
		if("USER".equalsIgnoreCase(command[0])&& (loginStatus == 0 || loginStatus >1)){
			return new HttpUser();
		}
		else
			return new HttpError();
	}

}
