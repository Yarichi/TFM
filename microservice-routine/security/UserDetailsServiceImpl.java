package TFM.microservice.appusers.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import TFM.microservice.appusers.VO.AppLoginVO;
import TFM.microservice.appusers.VO.AppUserInputVO;
import TFM.microservice.appusers.repositories.AppUserRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AppUserRepository repository;

    public UserDetailsServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        AppUserInputVO user = repository.findOneByAlias(alias);
        AppLoginVO loginUser = new AppLoginVO(user.getAlias(), user.getPassword());
        if (loginUser == null) {
            throw new UsernameNotFoundException(alias);
        }
        return new User(loginUser.getAlias(), loginUser.getPassword(), emptyList());
    }
    
    public UserDetails loadUserById(String userId) throws UsernameNotFoundException {
        AppUserInputVO user = repository.findOneByUserId(userId);
        AppLoginVO loginUser = new AppLoginVO(user.getAlias(), user.getPassword());
        if (loginUser == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new User(loginUser.getAlias(), loginUser.getPassword(), emptyList());
    }

}