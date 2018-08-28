package TFM.microservice.webusers.VO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import TFM.microservice.webusers.exceptions.BadWebUserInput;

@Document(collection="web_users")
public class WebUserSelectOutputVO {
	private String id;
	
	private String name;
	private String lastName;
	private String telegramAlias;
	private String occupation;
	
	private WebUserSelectOutputVO() {}
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelegramAlias() {
		return telegramAlias;
	}

	public void setTelegramAlias(String telegramAlias) {
		this.telegramAlias = telegramAlias;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
		
}
