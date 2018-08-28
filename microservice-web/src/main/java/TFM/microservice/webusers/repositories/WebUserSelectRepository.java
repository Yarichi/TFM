package TFM.microservice.webusers.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.webusers.VO.RolesInputVO;
import TFM.microservice.webusers.VO.WebUserInputVO;
import TFM.microservice.webusers.VO.WebUserSelectOutputVO;

public interface WebUserSelectRepository extends MongoRepository<WebUserSelectOutputVO, Integer>{
	
}
