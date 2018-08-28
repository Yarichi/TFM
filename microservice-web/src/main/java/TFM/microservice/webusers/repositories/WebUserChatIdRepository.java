package TFM.microservice.webusers.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.webusers.VO.PermissionsInputVO;
import TFM.microservice.webusers.VO.UserChatIdVO;

public interface WebUserChatIdRepository extends MongoRepository<UserChatIdVO, Integer>{
	
	@Query("{ 'alias' : ?0 }")
	UserChatIdVO findOneByAlias(String alias);
	
	
	
}
