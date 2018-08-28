package TFM.microservice.routines.VO;

import java.util.List;

public class TaskVO {
	private String name;
	private String description;
	private String type;
	private Long start_date;
	private Long final_date;
	private List<String> objects_rewards;
	private String npc_id;
	private String room_id;
	private Integer order;
	
	
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getObjects_rewards() {
		return objects_rewards;
	}
	public void setObjects_rewards(List<String> objects_rewards) {
		this.objects_rewards = objects_rewards;
	}
	public String getNpc_id() {
		return npc_id;
	}
	public void setNpc_id(String npc_id) {
		this.npc_id = npc_id;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	
	
}
