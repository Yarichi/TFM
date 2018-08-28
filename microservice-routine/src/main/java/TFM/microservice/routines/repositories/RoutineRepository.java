package TFM.microservice.routines.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.routines.VO.RoutineInputVO;

public interface RoutineRepository extends MongoRepository<RoutineInputVO, Integer>{

	@Query("{ 'id' : ?0 }")
	RoutineInputVO findOneById(String id);
	
	@Query("{ 'name' : ?0 }")
	RoutineInputVO findOneByName(String name);
}
