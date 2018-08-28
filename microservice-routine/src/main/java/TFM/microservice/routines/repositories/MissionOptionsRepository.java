package TFM.microservice.routines.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.routines.VO.MissionOptionsVO;

public interface MissionOptionsRepository extends MongoRepository<MissionOptionsVO, String>{

	@Query("{ 'name_template' : ?0 , 'name_field' : ?1}")
	MissionOptionsVO findAllByTemplateNameAndFieldName(String name_template, String name_field);
}
