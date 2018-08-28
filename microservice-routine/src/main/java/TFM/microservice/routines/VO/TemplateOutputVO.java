package TFM.microservice.routines.VO;

import java.util.List;
import java.util.Map;

public class TemplateOutputVO {

	private String name;
	private Map<String, List<String>> fields;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, List<String>> getFields() {
		return fields;
	}
	public void setFields(Map<String, List<String>> fields) {
		this.fields = fields;
	}
	public TemplateOutputVO(String name, Map<String, List<String>> fields) {
		super();
		this.name = name;
		this.fields = fields;
	}
	
	public void addFields(String key, List<String> value) {
		this.fields.put(key, value);
	}
}
