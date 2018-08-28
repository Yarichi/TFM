package TFM.microservice.webusers.constants;

public class URLConstants {
	public final static String URL_WEBUSER_BASE = "/webUser";
	public final static String URL_WEBUSER_SELECT = URL_WEBUSER_BASE + "/select";
	public final static String URL_WEBUSER_ID = URL_WEBUSER_BASE + "/{userId}";
	public final static String URL_WEBUSER_MAIL = URL_WEBUSER_BASE + "/{mail}/mail";
	
	public final static String URL_ROLE_BASE = "/roles";
	public final static String URL_ROLE_ID = URL_ROLE_BASE + "/{roleId}";
	
	public final static String URL_WEBUSER_ROLE_BASE = URL_WEBUSER_ID + "/roles";
	public final static String URL_WEBUSER_ROLE_ID = URL_WEBUSER_ROLE_BASE + "/{roleId}";
	
	
	public final static String URL_PERM_BASE = "/permissions";
	public final static String URL_PERM_ID = URL_PERM_BASE + "/{id}";
	
	public final static String URL_BOT = "/bot";
	public final static String URL_BOT_CHAT_ID = "/webUser/{userId}/chatId";
	

	public final static String URL_WEBUSER_ROLE = URL_WEBUSER_ID + URL_ROLE_ID;
	public final static String URL_ROLE_PERM = URL_WEBUSER_ROLE_ID + URL_PERM_BASE;
	public final static String URL_USER_PERM = URL_WEBUSER_ID + URL_PERM_BASE;
	
	public final static String URL_LOGIN = "/login";
	
	
}
