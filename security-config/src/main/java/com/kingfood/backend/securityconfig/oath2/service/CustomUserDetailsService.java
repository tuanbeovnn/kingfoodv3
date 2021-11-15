package com.kingfood.backend.securityconfig.oath2.service;



import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.dbprovider.UserRepository;
import com.kingfood.backend.domains.entity.UserEntity;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.securityconfig.oath2.dto.UserPrincipalOauth2;
import org.apache.velocity.exception.ResourceNotFoundException;
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

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ID_NOT_FOUND)
        );
        return UserPrincipalOauth2.createPrincipalOauth2(user);
    }
}