package TapChatServer.MyChatServerTdd;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FactoryHttpCommand {

	private static final Logger LOGGER = LogManager.getLogger(FactoryHttpCommand.class);
	public IHttpProtocol getCmd(String string, int loginStatus) {
		
		String paramT = string.trim();
		String[] params = paramT.split(" ",2);
		String command = params[0];
		try{
		params = params[1].split(" ");
		}catch (IndexOutOfBoundsException e){
			LOGGER.error(e);
			params = null;
		}
		if ("USER".equalsIgnoreCase(command) && (loginStatus == 0 || loginStatus > 1)) {
			return new HttpUser(params);
		}
		if ("PASS".equalsIgnoreCase(command) && loginStatus == 1) {
			return new HttpPass();
		} else
			return new HttpError();
	}

}
