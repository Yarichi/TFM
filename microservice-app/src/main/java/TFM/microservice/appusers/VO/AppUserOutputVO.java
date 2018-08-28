package TFM.microservice.appusers.VO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "app_users")
public class AppUserOutputVO {
	private String id;
	private String alias;
	private Integer level;
	private String type_template;
	private String role;
	private List<String> missions;
	private String mail;
	private String phone;
	private String telegramAlias;
	private String position;
	private List<String> friends;
	private Integer experience;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public String getUserId() {
		return id;
	}
	public void setUserId(String userId) {
		this.id = userId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getType_template() {
		return type_template;
	}
	public void setType_template(String type_template) {
		this.type_template = type_template;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getMissions() {
		return missions;
	}
	public void setMissions(List<String> missions) {
		this.missions = missions;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	public Boolean validateInput() {
		return true;
	}
	public void update(AppUserOutputVO new_user) {
		this.id = new_user.getUserId();
		this.alias = new_user.getAlias();
		this.level = new_user.getLevel();
		this.type_template = new_user.getType_template();
		this.role = new_user.getRole();
		this.missions = new_user.getMissions();
		this.mail = new_user.getMail();
		this.phone = new_user.getPhone();
		this.telegramAlias = new_user.getTelegramAlias();
		this.position = new_user.getPosition();
		this.friends = new_user.getFriends();
	}
}
