package TFM.microservice.appusers.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import TFM.microservice.appusers.VO.AppUserFriendsStringVO;
import TFM.microservice.appusers.VO.AppUserInputVO;
import TFM.microservice.appusers.VO.FriendVO;

public interface AppUserRepository extends MongoRepository<AppUserInputVO, Integer>{

	@Query("{ 'alias' : ?0 }")
	AppUserInputVO findOneByAlias(String alias);
	
	@Query("{ 'mail' : ?0 }")
	Boolean existsByMail(String mail);
	
	@Query("{ '_id' : ?0 }")
	AppUserInputVO findOneByUserId(String userId);
	
	@Query(value="{ 'alias' : ?0}", fields="{ 'friends' : 1}")
	AppUserFriendsStringVO findFriendsByAlias(String alias);
	
	@Query(value="{ 'alias' : ?0}", fields="{ 'alias' : 1, 'role' : 1, 'level' : 1}")
	FriendVO findFriendInfoByAlias(String alias);
}
