package TFM.microservice.routines.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.routines.VO.UserChatIdVO;


public interface WebUserChatIdRepository extends MongoRepository<UserChatIdVO, String>{
	
	@Query("{ 'alias' : ?0 }")
	UserChatIdVO findOneByAlias(String alias);
	
	
	
}
