package TFM.microservice.appusers.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
public class SecurityProperties {

	private String header;
	private String token_prefix;
	private long expiration_date;
	private String secret;
	
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getToken_prefix() {
		return token_prefix;
	}
	public void setToken_prefix(String token_prefix) {
		this.token_prefix = token_prefix;
	}
	public long getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(long expiration_date) {
		this.expiration_date = expiration_date;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	

    //Getters and Setters go here
}
