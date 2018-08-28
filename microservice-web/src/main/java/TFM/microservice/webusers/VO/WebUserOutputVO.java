package TFM.microservice.webusers.VO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import TFM.microservice.webusers.exceptions.BadWebUserInput;

@Document(collection="web_users")
public class WebUserOutputVO {
	private String id;
	
	private String name;
	private String lastName;
	private String mail;
	private String phone;
	private String telegramAlias;
	private Boolean isNPC;
	private List<String> roles;
	private String occupation;
	private Long lastAccess;
	private String lastAction;
	private String password;
	
	private WebUserOutputVO() {}
	
	public String getUserId() {
		return id;
	}
	public void setUserId(String userId) {
		this.id = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelegramAlias() {
		return telegramAlias;
	}
	public void setTelegramAlias(String telegramAlias) {
		this.telegramAlias = telegramAlias;
	}
	public Boolean getIsNPC() {
		return isNPC;
	}
	public void setIsNPC(Boolean isNPC) {
		this.isNPC = isNPC;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(Long lastAccess) {
		this.lastAccess = lastAccess;
	}
	public String getLastAction() {
		return lastAction;
	}
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean validateInput() throws BadWebUserInput{
		return true;
	}
	
	public void update(WebUserOutputVO newUser) {
		this.name = newUser.getName();
		this.lastName = newUser.getLastName();
		this.mail = newUser.getMail();
		this.phone = newUser.getPhone();
		this.telegramAlias = newUser.getTelegramAlias();
		this.isNPC = newUser.getIsNPC();
		this.roles = newUser.getRoles();
		this.occupation = newUser.getOccupation();
		this.lastAccess = newUser.getLastAccess();
		this.lastAction = newUser.getLastAction();
		this.password = newUser.getPassword();
	}
	
}
