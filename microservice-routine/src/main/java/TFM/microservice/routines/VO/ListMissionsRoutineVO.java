package TFM.microservice.routines.VO;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "missions_routine")
public class ListMissionsRoutineVO {
	@JsonIgnore
	private String id;
	private String routine_id;
	private List<MissionVO> missions;
	
	public ListMissionsRoutineVO() {}
	public ListMissionsRoutineVO(String routine_id, List<MissionVO> missions) {
		super();
		this.routine_id = routine_id;
		this.missions = missions;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoutine_id() {
		return routine_id;
	}
	public void setRoutine_id(String routine_id) {
		this.routine_id = routine_id;
	}
	public List<MissionVO> getMissions() {
		return missions;
	}
	public void setMissions(List<MissionVO> missions) {
		this.missions = missions;
	}
	
	
}
