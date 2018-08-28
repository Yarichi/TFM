package TFM.microservice.webusers.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import TFM.microservice.webusers.VO.WebLoginVO;
import TFM.microservice.webusers.VO.WebUserInputVO;
import TFM.microservice.webusers.repositories.WebUserRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private WebUserRepository repository;

    public UserDetailsServiceImpl(WebUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        WebUserInputVO user = repository.findOneByMail(mail);
        WebLoginVO loginUser = new WebLoginVO(user.getMail(), user.getPassword());
        if (loginUser == null) {
            throw new UsernameNotFoundException(mail);
        }
        return new User(loginUser.getMail(), loginUser.getPassword(), emptyList());
    }

	public UserDetails loadUserById(String userId) {
	 	WebUserInputVO user = repository.findOneByUserId(userId);
        WebLoginVO loginUser = new WebLoginVO(user.getMail(), user.getPassword());
        if (loginUser == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new User(loginUser.getMail(), loginUser.getPassword(), emptyList());
	}
}