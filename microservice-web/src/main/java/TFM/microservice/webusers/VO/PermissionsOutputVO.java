package TFM.microservice.webusers.VO;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="web_permissions")
public class PermissionsOutputVO {
	private String id;
	private String user_or_role;
	private String name;
	private String resource;
	private Integer create;
	private Integer read;
	private Integer update;
	private Integer delete;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_or_role() {
		return user_or_role;
	}
	public void setUser_or_role(String user_or_role) {
		this.user_or_role = user_or_role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getCreate() {
		return create;
	}
	public void setCreate(Integer create) {
		this.create = create;
	}
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
	public Integer getUpdate() {
		return update;
	}
	public void setUpdate(Integer update) {
		this.update = update;
	}
	public Integer getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	
	
	
}
