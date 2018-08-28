package TFM.microservice.appusers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import TFM.microservice.appusers.VO.AppUserFriendsStringVO;
import TFM.microservice.appusers.VO.AppUserFriendsVO;
import TFM.microservice.appusers.VO.AppUserInputVO;
import TFM.microservice.appusers.VO.ExperienceMessage;
import TFM.microservice.appusers.VO.FriendVO;
import TFM.microservice.appusers.VO.AppUserInputVO;
import TFM.microservice.appusers.repositories.AppUserRepository;

@Component
public class AppUsersService {
	
	@Autowired
	private AppUserRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	@Value("${user.experience.constant}")
	private Integer experience_constant;
	
	public AppUserInputVO getAppUser(String alias) {
		if (!alias.isEmpty()) {
			return repository.findOneByAlias(alias);
		}
		else return null;
	}
	
	public List<AppUserInputVO> getAppUsers() {
		return repository.findAll();
	}
	
	public Boolean setAppUser(AppUserInputVO user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setFriends(new ArrayList<String>());
		user.setLevel(1);
		user.setExperience(0);
		user.setMissions(new ArrayList<String>());
		if (repository.insert(user) != null)
			return false;
		else return true;
	}
	
	public Boolean updateAppUser(String alias, AppUserInputVO user) {
		AppUserInputVO oldUser;
		Boolean updated = false;
		if ((oldUser = repository.findOneByAlias(alias)) != null) {
			repository.delete(oldUser);
			repository.save(user);
			updated = true;
		}
		return updated;
	}

	public Boolean deleteAppUser(String alias) {
		AppUserInputVO user;
		Boolean deleted = false;
		if ((user = repository.findOneByAlias(alias)) != null) {
			repository.delete(user);
			deleted = true;
		}
		return deleted;
	}

	public AppUserFriendsVO getAppUserFriends(String alias) {
		if (!alias.isEmpty()) {
			AppUserFriendsVO friends_return = new AppUserFriendsVO();
			AppUserFriendsStringVO friends = repository.findFriendsByAlias(alias);
			System.out.println("FRIENDS: " + friends.getFriends().toString());
			for( String friend : friends.getFriends()) {
				FriendVO info = repository.findFriendInfoByAlias(friend);
				System.out.println("FRIEND: " + info.toString());
				friends_return.addFriend(info);
			}
			return friends_return;
		}
		else return null;
	}
	
	public Boolean addAppUserFriend(String alias, String aliasFriend) {
		AppUserInputVO oldUser;
		Boolean updated = false;
		if ((oldUser = repository.findOneByAlias(alias)) != null &&
				repository.findOneByAlias(aliasFriend) != null) {
			oldUser.addFriend(aliasFriend);
			repository.save(oldUser);
			updated = true;
		}
		return updated;
	}
	
	public Boolean deleteAppUserFriend(String alias, String aliasFriend) {
		AppUserInputVO oldUser;
		Boolean updated = false;
		if ((oldUser = repository.findOneByAlias(alias)) != null &&
				repository.findOneByAlias(aliasFriend) != null) {
			oldUser.getFriends().remove(aliasFriend);
			repository.save(oldUser);
			updated = true;
		}
		return updated;
	}

	public void updateExperienceUser(String alias, ExperienceMessage experience) {
		AppUserInputVO user = repository.findOneByAlias(alias);
		if (user != null) {
			Integer experience_total = user.getExperience() + experience.getExperience();
			if (experience_total >= user.getLevel() * this.experience_constant) {
				user.setExperience(Math.max(0, experience_total - (user.getLevel() * this.experience_constant)));
				user.setLevel(user.getLevel()+1);
				
			}
			else {
				user.setExperience(experience_total);
			}
			repository.save(user);
		}
		
	}
}
