package TFM.microservice.routines.VO;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task_maps")
public class TaskMapVO {
	private String name;
	private List<String> template_options;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTemplate_options() {
		return template_options;
	}
	public void setTemplate_options(List<String> template_options) {
		this.template_options = template_options;
	}
}
