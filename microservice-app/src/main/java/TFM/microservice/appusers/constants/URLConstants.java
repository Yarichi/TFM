package TFM.microservice.appusers.constants;

public class URLConstants {
	public final static String URL_APPUSER_BASE = "/appUser";
	public final static String URL_APPUSER_ALIAS = URL_APPUSER_BASE + "/{alias}";
	public static final String URL_APPUSER_FRIENDS = URL_APPUSER_ALIAS + "/friends";
	public static final String URL_APPUSER_FRIENDS_ADD = URL_APPUSER_FRIENDS + "/{aliasFriend}";
	public static final String URL_EXPERIENCE_USER = "/user/{alias}/experience";
}
