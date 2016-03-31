package com.itechart.kalenik.service.security;

import com.itechart.kalenik.dao.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.itechart.kalenik.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.itechart.kalenik.dao.entity.User user = userService.findByUsername(username);
        if(user==null)return null;
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles= user.getUserRoleList();
        for(UserRole role : userRoles ){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
