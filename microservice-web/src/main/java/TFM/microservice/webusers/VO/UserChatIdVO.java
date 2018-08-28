package TFM.microservice.webusers.VO;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="web_user_chat_id")
public class UserChatIdVO {
	private String alias;
	private String chat_id;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getChat_id() {
		return chat_id;
	}
	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}
	public UserChatIdVO(String alias, String chat_id) {
		super();
		this.alias = alias;
		this.chat_id = chat_id;
	}
	public UserChatIdVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
