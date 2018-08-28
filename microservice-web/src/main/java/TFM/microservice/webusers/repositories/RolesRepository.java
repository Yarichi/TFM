package TFM.microservice.webusers.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.webusers.VO.RolesInputVO;
import TFM.microservice.webusers.VO.WebUserInputVO;

public interface RolesRepository extends MongoRepository<RolesInputVO, Integer>{
	
	@Query("{ '_id' : ?0 }")
	RolesInputVO findOneById(String id);
	
}
