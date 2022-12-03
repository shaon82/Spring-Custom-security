package com.shaon.springsecurity.configuration;

import com.shaon.springsecurity.model.AuthenticatedUser;
import com.shaon.springsecurity.model.UserEntity;
import com.shaon.springsecurity.repository.UserRepository;
import com.shaon.springsecurity.service.UserDetailsServiceImpl;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity userEntity = userDetailsService.getUserByUsername(username);
        if (userEntity != null && new BCryptPasswordEncoder().matches(password, userEntity.getPassword())){

            //DTO
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(userEntity.getName(), userEntity.getUsername(), password);

            List<GrantedAuthority> authorities = new ArrayList<>();

            return new UsernamePasswordAuthenticationToken(authenticatedUser, authentication.getCredentials(), authorities);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
