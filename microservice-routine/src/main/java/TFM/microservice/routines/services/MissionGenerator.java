package TFM.microservice.routines.services;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import TFM.microservice.routines.repositories.*;
import TFM.microservice.routines.VO.*;
import TFM.microservice.routines.constants.URLConstants;


@Component
public class MissionGenerator {
	
	@Autowired
	private RoutineRepository repository_routines;
	
	@Autowired
	private TaskMapRepository repository_task_map;
	

	@Autowired
	private MissionsRoutineRepository repository_missions_routine;

	@Autowired
	private TemplateRepository repository_template;
	
	@Autowired
	private MissionOptionsRepository repository_options;
	

	@Value("${microservice.app_users.host}")
	private String app_users_host;
	
	@Value("${microservice.app_users.port}")
	private Integer app_users_port;
	
	
	@Value("${user.experience.variable.min}")
	private Integer user_exp_min;
	
	@Value("${user.experience.variable.max}")
	private Integer user_exp_max;
	
	
	
	public RoutineInputVO getRoutine(String id) {
		if (!id.isEmpty()) {
			return repository_routines.findOneById(id);
		}
		else return null;
	}

	public List<MissionVO> generateMissions(String id) throws Exception{
		ListMissionsRoutineVO missions_routine;
		if((missions_routine = repository_missions_routine.findOneByRoutineId(id)) != null) {
			return missions_routine.getMissions();
		}
		RoutineInputVO routine = this.getRoutine(id);
		List<MissionVO> missions = new ArrayList<MissionVO>();
		List<TaskVO> tasks = routine.getTasks();
		Random rand = new Random();
		for (int i = 0; i < tasks.size(); i++) {
			TaskVO t = tasks.get(i);
			String type = t.getType();
			System.out.println(type);
			TaskMapVO task_map = repository_task_map.findOneByName(type);
			String mission_type = task_map.getTemplate_options().get(new Random().nextInt(task_map.getTemplate_options().size()));
			List<String> template_fields = repository_template.findOneByName(mission_type).getFields();
			if (template_fields != null) {
				String text_mission = "";
				for(String field : template_fields) {
					MissionOptionsVO field_options = repository_options.findAllByTemplateNameAndFieldName(mission_type, field);
					if (field_options != null) text_mission+=field_options.getOptions().get(new Random().nextInt(field_options.getOptions().size())) + '\n';
					else throw new Exception();
				}

				missions.add(new MissionVO(routine.getName(), routine.getName(), text_mission, 0, mission_type, t.getStart_date(), t.getFinal_date(), i, t.getRoom_id(), rand.nextInt(user_exp_max-user_exp_min)+user_exp_min));
			}
			//TODO: Excepcion personalizada
			else throw new Exception();
			
		}
		if (missions != null) repository_missions_routine.save(new ListMissionsRoutineVO(id, missions));
		return missions;
	}
	
	public Boolean setStatus(String missionId, Integer status) throws Exception {
		String decoded = new String(Base64.decodeBase64(missionId));
		String[] decoded_parts = decoded.split("\\+");
		ListMissionsRoutineVO list = repository_missions_routine.findOneByRoutineId(decoded_parts[0]);
		List<MissionVO> missions = list.getMissions();
		String username = decoded_parts[0].split("_")[0];
		Integer experience = 0;
		Boolean keep = true;
		for (int i = 0; (i < missions.size() && keep); i++) {
			MissionVO mission = missions.get(i);
			if(mission.getOrder() == Integer.parseInt(decoded_parts[1])) {
				mission.setStatus(status);
				experience = mission.getExperience();
				keep = false;
			}
		}
		repository_missions_routine.save(list);
		if(status == 1) this.updateExperience(username, experience);
		return true;
	}
	
	public Boolean updateExperience(String username, Integer experience) throws Exception {
		String aux_url = String.format("http://%s:%d%s", this.app_users_host, this.app_users_port,
				String.format(URLConstants.URL_USER_EXPERIENCE, username));
		
		String body =  String.format("{\"experience\": %d}", experience);
		URL url = new URL(aux_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type","application/json");
		con.setDoOutput(true);
		byte[] outputInBytes = body.getBytes("UTF-8");
		OutputStream os = con.getOutputStream();
		os.write( outputInBytes );    
		os.close();
		Integer status = con.getResponseCode();
		
		con.disconnect();
		return status == 200;
	}
}

