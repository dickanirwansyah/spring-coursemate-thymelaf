package com.fusi24.springcoursemate.service;

import com.fusi24.springcoursemate.entity.User;
import com.fusi24.springcoursemate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Maaf user dengan username : "+username+" tidak ada");
        }

        String role = user.getRole();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(authority);

        boolean enabled = user.isActive();
        boolean userNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean userNonLocked = true;

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswordHash(),
                enabled,
                userNonExpired,
                credentialsNonExpired,
                userNonLocked,
                grantedAuthorities
        );

        return userDetails;
    }
}
