package TFM.microservice.routines.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.routines.VO.ListMissionsRoutineVO;
import TFM.microservice.routines.VO.MissionOptionsVO;

public interface MissionsRoutineRepository extends MongoRepository<ListMissionsRoutineVO, String>{

	@Query("{ 'routine_id' : ?0}")
	ListMissionsRoutineVO findOneByRoutineId(String routine_id);

	@Query("{ '_id' : ?0}")
	ListMissionsRoutineVO findOneById(String string);
}
