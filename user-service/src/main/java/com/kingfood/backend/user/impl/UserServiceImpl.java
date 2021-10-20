package com.kingfood.backend.user.impl;


import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.RoleRepository;
import com.kingfood.backend.dbprovider.UserRepository;
import com.kingfood.backend.domains.entity.RoleEntity;
import com.kingfood.backend.domains.entity.UserEntity;
import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.UserResponse;
import com.kingfood.backend.exceptions.CommonUtils;
import com.kingfood.backend.exceptions.CustomException;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findOneByUserName(userRequest.getUserName());
        if (userEntity != null) {
            throw new CustomException("User exists", CommonUtils.putError("username", ""));
        }
        List<RoleEntity> roleEntities = roleRepository.findByIdIn(userRequest.getRoleIds().stream().map(e -> e).collect(Collectors.toList()));
        userEntity = Converter.toModel(userRequest, UserEntity.class);
        userEntity.setRoles(roleEntities);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setStatus(1);
        userEntity = userRepository.save(userEntity);
        return Converter.toModel(userEntity, UserResponse.class);
    }
}
