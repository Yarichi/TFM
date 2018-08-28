package TFM.microservice.routines.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.routines.VO.TemplateVO;

public interface TemplateRepository extends MongoRepository<TemplateVO, String>{

	@Query("{ 'name_template' : ?0 }")
	TemplateVO findOneByName(String name_template);
}
