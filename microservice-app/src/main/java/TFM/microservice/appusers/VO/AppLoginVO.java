package TFM.microservice.appusers.VO;

public class AppLoginVO {
	private String alias;
	private String password;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AppLoginVO(String alias, String password) {
		super();
		this.alias = alias;
		this.password = password;
	}
	
	public AppLoginVO() {}
	
}
