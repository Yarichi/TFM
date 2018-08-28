package TFM.microservice.geoposition.constants;

public class URLConstants {
	public final static String URL_LOCATION_BASE = "/location";
	public final static String URL_LOCATION_MAC = URL_LOCATION_BASE + "/user/{ip_address}";
	public final static String URL_IMAGE_BY_CBF = URL_LOCATION_BASE + "/map/campus/{campus}/building/{building}/floor/{floor}";
	public final static String URL_IMAGE_BY_NAME = URL_LOCATION_BASE + "/map/name/{name}";
	public final static String URL_MAP_INFO = URL_LOCATION_BASE + "/map/info/campus/{campus}/building/{building}/floor/{floor}";
	public final static String URL_ZONE_COORDINATE = URL_MAP_INFO + "/zone/{zone}";
	
	public final static String URL_STRUCTURE_INFO = URL_LOCATION_BASE + "/structure";
	
	public final static String URL_CONFIG_BASE = "/config";
	
	
	public final static String GET_MAP_BY_CBF = "/api/config/v1/maps/image/%s/%s/%s";
	public final static String GET_MAP_BY_NAME = "/api/config/v1/maps/imagesource/%s";
	public final static String GET_USER_POSITION_BY_IP = "/api/location/v3/clients?ipAddress=%s";
	public final static String GET_MAP_INFO = "/api/config/v1/maps/info/%s/%s/%s";
	public static final String GET_STRUCTURE_INFO = "/api/config/v1/maps/count";
	
}
