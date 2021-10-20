package com.kingfood.backend.securityconfig.oath2.config;

import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public abstract class AbstractPasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    protected final UserDetailsService service;

    protected AbstractPasswordAuthenticationProvider(UserDetailsService service) {
        this.service = service;
    }

    protected UserDetails findUserDetail(String username) {
        UserDetails loadedUser = service.loadUserByUsername(username);
        return loadedUser;
    }

}
