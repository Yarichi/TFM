package TFM.microservice.routines.VO;

import java.util.List;

public class MissionVO {
	private String routine;
	private String username;
	private String text;
	private Integer status;
	private String type;
	private Long start_date;
	private Long final_date;
	private Integer order;
	private Integer experience;
	private String room_id;
	
	public MissionVO(String routine, String username, String text, Integer status, String type, Long start_date,
			Long final_date, Integer order, String room_id, Integer exp) {
		super();
		this.routine = routine;
		this.username = username;
		this.text = text;
		this.status = status;
		this.type = type;
		this.start_date = start_date;
		this.final_date = final_date;
		this.order = order;
		this.room_id = room_id;
		this.experience = exp;
	}
	
	public MissionVO() {super();}
	
	
	
	public Integer getExperience() {
		return experience;
	}



	public void setExperience(Integer experience) {
		this.experience = experience;
	}



	public String getRoutine() {
		return routine;
	}
	public void setRoutine(String routine) {
		this.routine = routine;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getStart_date() {
		return start_date;
	}
	public void setStart_date(Long start_date) {
		this.start_date = start_date;
	}
	public Long getFinal_date() {
		return final_date;
	}
	public void setFinal_date(Long final_date) {
		this.final_date = final_date;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}



	public String getRoom_id() {
		return room_id;
	}



	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	
	
	

	
}
