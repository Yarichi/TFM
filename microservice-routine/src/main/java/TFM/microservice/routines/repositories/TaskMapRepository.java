package TFM.microservice.routines.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.routines.VO.TaskMapVO;

public interface TaskMapRepository extends MongoRepository<TaskMapVO, String>{

	@Query("{ 'name' : ?0 }")
	TaskMapVO findOneByName(String name);
}
