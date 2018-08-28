package TFM.microservice.routines.services;

import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import TFM.microservice.routines.VO.RoutineInputVO;
import TFM.microservice.routines.VO.TaskVO;
import TFM.microservice.routines.repositories.RoutineRepository;

@Component
public class RoutinesService {
	
	@Autowired
	private RoutineRepository repository;
	
	@Autowired
	private BotService service;
	@Autowired
	private MissionGenerator generator;
	
	public RoutineInputVO getRoutine(String routineId) {
		if (!routineId.isEmpty()) {
			return repository.findOneById(routineId);
		}
		else return null;
	}
	
	public List<RoutineInputVO> getRoutines() {
		return repository.findAll();
	}
	
	public Boolean setRoutine(RoutineInputVO routine) throws Exception {
		repository.save(routine);
		generator.generateMissions(routine.getId());
		for (TaskVO t : routine.getTasks()) {
			String mission_id = Base64.encodeBase64String((routine.getId() + "+" + t.getOrder()).getBytes());
			String aux = new String(Base64.decodeBase64(t.getNpc_id()));
			
			service.sendMessage(String.format("Mission with ID %s has been assigned to you, the user with alias %s will come by 2 at %s",mission_id, routine.getAlias_user(), t.getRoom_id()),
					mission_id, 
					aux.split("\\+")[3]);
		}
		return true;
	}
	
	public Boolean updateRoutine(String routineId, RoutineInputVO routine) throws Exception {
		RoutineInputVO oldRoutine;
		Boolean updated = false;
		if ((oldRoutine = repository.findOneById(routineId)) != null) {
			repository.delete(oldRoutine);
			repository.save(routine);
			generator.generateMissions(routine.getId());
			updated = true;
		}
		return updated;
	}

	public Boolean deleteRoutine(String routineId) {
		RoutineInputVO oldRoutine;
		Boolean deleted = false;
		if ((oldRoutine = repository.findOneById(routineId)) != null) {
			repository.delete(oldRoutine);
			deleted = true;
		}
		return deleted;
	}
}
