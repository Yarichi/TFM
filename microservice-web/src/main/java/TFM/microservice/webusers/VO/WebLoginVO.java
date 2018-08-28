package TFM.microservice.webusers.VO;

public class WebLoginVO {
	private String mail;
	private String password;
	public String getMail() {
		return mail;
	}
	
	public WebLoginVO() {}
	
	public WebLoginVO(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
