package service.security;

import dao.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        dao.entity.User user = userService.findByUsername(username);

        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        List<UserRole> userRoles= user.getUserRoleList();
        String role = "";
        if(userRoles!=null && userRoles.size()>0){
            role = userRoles.get(0).getName();
        }
        auths.add(new SimpleGrantedAuthority(role));
        return new User(user.getUsername(), user.getPassword(), auths);
    }
}
