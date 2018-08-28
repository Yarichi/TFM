package TFM.microservice.webusers.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.webusers.VO.RolesInputVO;
import TFM.microservice.webusers.VO.WebUserInputVO;
import TFM.microservice.webusers.VO.WebUserSelectOutputVO;

public interface WebUserRepository extends MongoRepository<WebUserInputVO, Integer>{

	@Query("{ 'mail' : ?0 }")
	WebUserInputVO findOneByMail(String mail);
	
	@Query("{ '_id' : ?0 }")
	WebUserInputVO findOneByUserId(String userId);
	
	@Query("{ 'telegramAlias' : ?0 }")
	WebUserInputVO findOneByTelegramAlias(String telegramAlias);
	
	@Query("{ 'mail' : ?0 }")
	Boolean existsByMail(String mail);
	
	@Query(value="{'_id' : ?0}", fields="{'roles' : 1}")
	RolesInputVO findRolesByUserId(String userId);
	
	
	List<WebUserSelectOutputVO> findAllById();
	
}
