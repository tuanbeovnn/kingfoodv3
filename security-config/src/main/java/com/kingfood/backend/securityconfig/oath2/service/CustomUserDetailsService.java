package com.kingfood.backend.securityconfig.oath2.service;



import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.dbprovider.UserRepository;
import com.kingfood.backend.domains.entity.UserEntity;
import com.kingfood.backend.securityconfig.oath2.dto.UserPrincipalOauth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByEmailOrUserNameAndStatus(email, email, AppConstant.ACTIVE.ACTIVE_STATUS);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return UserPrincipalOauth2.createPrincipalOauth2(userEntity);
    }
}