package TFM.microservice.routines.VO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "templates")
public class TemplateVO {
	private String name_template;
	private List<String> fields;
	
	
	public String getName_template() {
		return name_template;
	}
	public void setName_template(String name_template) {
		this.name_template = name_template;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}

}
