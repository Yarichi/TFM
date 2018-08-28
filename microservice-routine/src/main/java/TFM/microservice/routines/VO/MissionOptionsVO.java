package TFM.microservice.routines.VO;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mission_options")
public class MissionOptionsVO {
	private String name_template;
	private String name_field;
	private List<String> options;
	
	public String getName_template() {
		return name_template;
	}
	public void setName_template(String name_template) {
		this.name_template = name_template;
	}
	public String getName_field() {
		return name_field;
	}
	public void setName_field(String name_field) {
		this.name_field = name_field;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}	
	
}
