package TFM.microservice.webusers.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.webusers.VO.PermissionsInputVO;

public interface PermissionsRepository extends MongoRepository<PermissionsInputVO, Integer>{
	
	@Query("{ '_id' : ?0 }")
	PermissionsInputVO findOneById(String id);
	
	@Query("{ 'user_or_role' : 'r', 'name' : ?0}")
	List<PermissionsInputVO> findAllByRoleId(String roleId);
	
	
}
