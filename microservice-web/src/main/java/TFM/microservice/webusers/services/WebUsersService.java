package TFM.microservice.webusers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import TFM.microservice.webusers.VO.PermissionsInputVO;
import TFM.microservice.webusers.VO.RolesInputVO;
import TFM.microservice.webusers.VO.UserChatIdVO;
import TFM.microservice.webusers.VO.WebUserInputVO;
import TFM.microservice.webusers.VO.WebUserSelectOutputVO;
import TFM.microservice.webusers.repositories.PermissionsRepository;
import TFM.microservice.webusers.repositories.RolesRepository;
import TFM.microservice.webusers.repositories.WebUserChatIdRepository;
import TFM.microservice.webusers.repositories.WebUserRepository;
import TFM.microservice.webusers.repositories.WebUserSelectRepository;

@Component
public class WebUsersService {
	
	@Autowired
	private WebUserRepository repository;
	@Autowired
	private WebUserSelectRepository repository_select;
	@Autowired
	private RolesRepository roles_repository;
	@Autowired
	private PermissionsRepository perm_repository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private WebUserChatIdRepository chat_repository;
	
	public WebUserInputVO getWebUser(String userId) {
		if (!userId.isEmpty()) {
			return repository.findOneByUserId(userId);
		}
		else return null;
	}
	
	public List<WebUserInputVO> getWebUsers() {
		return repository.findAll();
	}
	
	public Boolean setWebUser(WebUserInputVO user) {
		user.setPassword(encoder.encode(user.getPassword()));
		if (repository.insert(user) != null)
			return false;
		else return true;
	}
	
	public Boolean updateWebUser(String userId, WebUserInputVO user) {
		WebUserInputVO oldUser;
		Boolean updated = false;
		if ((oldUser = repository.findOneByUserId(userId)) != null) {
			repository.delete(oldUser);
			repository.save(user);
			updated = true;
		}
		return updated;
	}
	
	public Boolean deleteWebUser(String userId) {
		WebUserInputVO user;
		Boolean deleted = false;
		if ((user = repository.findOneByUserId(userId)) != null) {
			repository.delete(user);
			deleted = true;
		}
		return deleted;
	}
	
	
	/**
	 * ROLES
	 */
	public List<RolesInputVO> getRoles() {
		return roles_repository.findAll();
	}
	
	public Boolean setRole(RolesInputVO role) {
		if (roles_repository.insert(role) != null)
			return false;
		else return true;
	}
	
	public Boolean updateRole(String id, RolesInputVO role) {
		RolesInputVO old;
		Boolean updated = false;
		if ((old = roles_repository.findOneById(id)) != null) {
			roles_repository.delete(old);
			roles_repository.save(role);
			updated = true;
		}
		return updated;
	}
	
	public Boolean deleteRole(String id) {
		RolesInputVO old;
		Boolean deleted = false;
		if ((old = roles_repository.findOneById(id)) != null) {
			roles_repository.delete(old);
			deleted = true;
		}
		return deleted;
	}
	
	/**
	 * PERMISSIONS
	 */
	
	public List<PermissionsInputVO> getPermissions() {
		return perm_repository.findAll();
	}
	
	public Boolean setPerm(PermissionsInputVO perm) {
		if (perm_repository.insert(perm) != null)
			return false;
		else return true;
	}
	
	public Boolean updatePerm(String id, PermissionsInputVO perm) {
		PermissionsInputVO old;
		Boolean updated = false;
		if ((old = perm_repository.findOneById(id)) != null) {
			perm_repository.delete(old);
			perm_repository.save(perm);
			updated = true;
		}
		return updated;
	}
	
	public Boolean deletePerm(String id) {
		PermissionsInputVO old;
		Boolean deleted = false;
		if ((old = perm_repository.findOneById(id)) != null) {
			perm_repository.delete(old);
			deleted = true;
		}
		return deleted;
	}

	public void addRoleToUser(String userId, String roleId) {
		WebUserInputVO user = repository.findOneByUserId(userId);
		if(!user.getRoles().contains(roleId)) {
			user.getRoles().add(roleId);
			repository.save(user);
		}	
	}
	
	public List<PermissionsInputVO> getPermissionsRole(String roleId){
		return perm_repository.findAllByRoleId(roleId);
	}

	public List<PermissionsInputVO> getPermissionsUser(String userId) {
		WebUserInputVO user = repository.findOneByUserId(userId);
		List<PermissionsInputVO> perms = new ArrayList<PermissionsInputVO>();
		if(user != null) {
			List<String> roles = user.getRoles();
			perms = perm_repository.findAllByRoleId(roles.get(0));
			for (int i = 1; i < roles.size(); i++) {
				List<PermissionsInputVO> perms_aux = perm_repository.findAllByRoleId(roles.get(i));
				perms.stream().map(p -> this.includePermissions(p, 
						perms_aux.stream().filter(x -> x.getResource() == p.getResource()).collect(Collectors.toList()).get(0)));
			}
		}
		return perms;
		
		
	}
	
	public PermissionsInputVO includePermissions(PermissionsInputVO p, PermissionsInputVO x) {
		p.setCreate(x.getCreate());
		p.setRead(x.getRead());
		p.setUpdate(x.getUpdate());
		p.setDelete(x.getDelete());
		return p;
	}

	public WebUserInputVO getWebUserByMail(String mail) {
		if (!mail.isEmpty()) {
			return repository.findOneByMail(mail);
		}
		else return null;
	}

	public List<WebUserSelectOutputVO> getWebUsersSelect() {
		List<WebUserSelectOutputVO> list = repository_select.findAll();
		list = list.stream().filter(user -> user.getTelegramAlias() != null).collect(Collectors.toList());
		return list;
	}

	public UserChatIdVO getChatId(String userId) {
		return chat_repository.findOneByAlias(userId);
	}
}
