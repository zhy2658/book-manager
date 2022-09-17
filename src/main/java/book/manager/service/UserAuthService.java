package book.manager.service;

import book.manager.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService implements UserDetailsService {

    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password = mapper.getPasswordByUserName(s);
        if(password == null) throw new UsernameNotFoundException("Login failed.Uname or pwd is error");
        return User.withUsername(s)
                .password(password)
                .roles("user")
                .build();
    }
}
