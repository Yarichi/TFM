package TFM.microservice.appusers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("TFM.microservice.appusers.repositories")
public class MongoConfig extends AbstractMongoConfiguration{

	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.port}")
	private Integer port;
	@Value("${spring.data.mongodb.username}")
	private String username;
	@Value("${spring.data.mongodb.password}")
	private String password;
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	
	@Override
	public MongoClient mongoClient() {
//		return new MongoClient(String.format("mongodb://%s:%s@%s:%d/%s",
//				this.username,
//				this.password,
//				this.host,
//				this.port,
//				this.database));
		return new MongoClient(this.host, this.port);
	}

	@Override
	protected String getDatabaseName() {
		return this.database;
	}

	
}
