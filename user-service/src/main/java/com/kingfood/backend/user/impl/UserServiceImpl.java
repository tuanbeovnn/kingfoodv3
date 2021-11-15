package com.kingfood.backend.user.impl;


import com.kingfood.backend.EmailUtils.EmailServiceUtil;
import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.RoleRepository;
import com.kingfood.backend.dbprovider.UserRepository;
import com.kingfood.backend.domains.dto.EmailDto;
import com.kingfood.backend.domains.dto.ForgotPasswordDto;
import com.kingfood.backend.domains.entity.RoleEntity;
import com.kingfood.backend.domains.entity.UserEntity;
import com.kingfood.backend.domains.redis.ForgotPassWordRedisDto;
import com.kingfood.backend.domains.redis.repository.ForgotPasswordRedisRepository;
import com.kingfood.backend.domains.request.ChangePasswordRequest;
import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.UserResponse;
import com.kingfood.backend.exceptions.CommonUtils;
import com.kingfood.backend.exceptions.CustomException;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.securityconfig.oath2.service.SecurityUtils;
import com.kingfood.backend.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.StringWriter;
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
    @Autowired
    private EmailServiceUtil emailServiceUtil;


    @Autowired
    private ForgotPasswordRedisRepository forgotPasswordRedisRepository;
    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findOneByUserName(userRequest.getUserName());
//        if (userEntity != null) {
//            throw new CustomException("User exists", CommonUtils.putError("username", ""));
//        }
        List<RoleEntity> roleEntities = roleRepository.findByIdIn(userRequest.getRoleIds().stream().map(e -> e).collect(Collectors.toList()));
        userEntity = Converter.toModel(userRequest, UserEntity.class);
        userEntity.setRoles(roleEntities);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setStatus(1);
        userEntity = userRepository.save(userEntity);
        return Converter.toModel(userEntity, UserResponse.class);
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        long expire = System.currentTimeMillis() + 20 * 60 * 1000;
        String signature = DigestUtils.sha256Hex(forgotPasswordDto.getEmail() + expire + secret);
        emailServiceUtil.sendEmailActiveCode(forgotPasswordDto.getEmail(), expire, signature);
        ForgotPassWordRedisDto forgotPassWordRedisDto = new ForgotPassWordRedisDto();
        forgotPassWordRedisDto.setEmail(forgotPasswordDto.getEmail());
        forgotPassWordRedisDto.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        forgotPasswordRedisRepository.save(forgotPassWordRedisDto);
    }

    @Override
    @Transactional
    public void resetPassword(EmailDto emailModel) {
        try {
            if (!DigestUtils.sha256Hex(emailModel.getEmail() + emailModel.getExpire() + secret).equals(emailModel.getSignature())) {
                throw new AppException(ErrorCode.SIGNATURE_NOT_CORRECT);
            }
            if (System.currentTimeMillis() > emailModel.getExpire()) {
                throw new AppException(ErrorCode.EXPIRED);
            }
            UserEntity userEntity = userRepository.findOneByEmailOrUserNameAndStatus(emailModel.getEmail(), emailModel.getEmail(), AppConstant.ACTIVE.ACTIVE_STATUS);
            if (userEntity == null) {
                throw new AppException(ErrorCode.ID_NOT_FOUND);
            }
            String newPassword = UUID.randomUUID().toString();
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
            emailServiceUtil.sendMail(emailModel.getEmail(), "RESET PASSWORD", "Current Password: " + newPassword);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        UserEntity userEntity = userRepository.findById(SecurityUtils.getPrincipal().getId()).orElseThrow((() -> new AppException(ErrorCode.ID_NOT_FOUND)));
        if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(userEntity);
            return true;
        } else {
            throw new AppException(ErrorCode.PASSWORD_DID_NOT_MATCH);
        }
    }

    @Override
    public UserResponse findUserById() {
        UserEntity userEntity = userRepository.findById(SecurityUtils.getPrincipal().getId()).orElseThrow((() -> new AppException(ErrorCode.ID_NOT_FOUND)));
        return Converter.toModel(userEntity, UserResponse.class);
    }
}
