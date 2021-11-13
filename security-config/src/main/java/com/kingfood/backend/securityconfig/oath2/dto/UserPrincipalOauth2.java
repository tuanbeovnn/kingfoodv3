package com.kingfood.backend.securityconfig.oath2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingfood.backend.domains.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class UserPrincipalOauth2 implements OAuth2User, UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
//    private Long userId;
    private String email;
    //private int hourToken;
    private Map<String, Object> attributes;


    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipalOauth2(String username, String password, String email, Long id, Collection<? extends GrantedAuthority> authorities /*, int hourToken*/) {
        this.username = username;
        this.password = password;
//        this.userId = userId;
        this.id = id;
        this.email = email;
        this.authorities = authorities;
        //this.hourToken = hourToken;
    }

    // get roles and set authorities
    public static UserPrincipalOauth2 createPrincipalOauth2(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(roleEntity ->
                new SimpleGrantedAuthority("ROLE_" + roleEntity.getCode())
        ).collect(Collectors.toList());
        return new UserPrincipalOauth2(
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getId(),
                authorities
                //userEntity.getHourToken()
        );
    }
    public static UserPrincipalOauth2 create(UserEntity user, Map<String, Object> attributes) {
        UserPrincipalOauth2 userPrincipal = UserPrincipalOauth2.createPrincipalOauth2(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return String.valueOf(username);
    }
}
