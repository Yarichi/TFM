package TFM.microservice.routines.VO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "routines")
public class RoutineOutputVO {
	private String id;
	private String name;
	private String description;
	private String alias_user;
	private String creator;
	private Long date_routine;
	private String last_mod_name;
	private long last_mod_date;
	private List<TaskVO> tasks;

	
	
	public Long getDate_routine() {
		return date_routine;
	}

	public void setDate_routine(Long date_routine) {
		this.date_routine = date_routine;
	}

	public String getAlias_user() {
		return alias_user;
	}

	public void setAlias_user(String alias_user) {
		this.alias_user = alias_user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLast_mod_name() {
		return last_mod_name;
	}

	public void setLast_mod_name(String last_mod_name) {
		this.last_mod_name = last_mod_name;
	}

	public long getLast_mod_date() {
		return last_mod_date;
	}

	public void setLast_mod_date(long last_mod_date) {
		this.last_mod_date = last_mod_date;
	}

	public List<TaskVO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskVO> tasks) {
		this.tasks = tasks;
	}
	
	public Boolean validateInput() {
		return true;
	}
	public void update(RoutineOutputVO new_routine) {
		this.creator = new_routine.getCreator();
		this.description = new_routine.getDescription();
		this.id = new_routine.getId();
		this.last_mod_name = new_routine.getLast_mod_name();
		this.last_mod_date = new_routine.getLast_mod_date();
		this.name = new_routine.getName();
		this.tasks = new_routine.getTasks();
	}
}
