package TFM.microservice.routines.constants;

public class URLConstants {
	public final static String URL_ROUTINE_BASE = "/routines";
	public final static String URL_ROUTINE_NAME = URL_ROUTINE_BASE + "/{routineId}";

	public final static String URL_GENERATE_MISSIONS = URL_ROUTINE_BASE + "/missions";
	public final static String URL_MISSION_COMPLETED = URL_ROUTINE_BASE + "/missions/{missionId}/completed";
	public final static String URL_MISSION_INCOMPLETED = URL_ROUTINE_BASE + "/missions/{missionId}/incompleted";
	
	
	public final static String URL_TASKMAP_BASE = "/taskMap";
	public final static String URL_TASKMAP_TEMPLATES = "/taskMap/{taskMap}";
	
	
	public static final String URL_BOT_MESSAGE = "/message";
	public static final String URL_USER_EXPERIENCE = "/user/%s/experience";

}
