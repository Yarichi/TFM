package TFM.microservice.appusers.VO;

public class FriendVO {
	private String alias;
	private String role;
	private Integer level;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "FriendVO [alias=" + alias + ", role=" + role + ", level=" + level + "]";
	}
	
	
	
	
}
