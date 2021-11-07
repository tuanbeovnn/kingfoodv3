package com.kingfood.backend.gateway.users;



import com.kingfood.backend.domains.dto.EmailDto;
import com.kingfood.backend.domains.dto.ForgotPasswordDto;
import com.kingfood.backend.domains.redis.ForgotPassWordRedisDto;
import com.kingfood.backend.domains.redis.repository.ForgotPasswordRedisRepository;
import com.kingfood.backend.domains.request.ChangePasswordRequest;
import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.UserResponse;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.responseBuilder.ResponseEntityBuilder;
import com.kingfood.backend.user.UserService;
import com.kingfood.backend.validation.DefaultSignUpValidationService;
import com.kingfood.backend.validation.SignUpValidationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;


/**
 * @Author: Tuan Nguyen
 */
@Api(
        tags = "User-API",
        description = "Providing api for User"
)
@RestController
@RequestMapping("/api")
public class UserAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpValidationService signUpValidationService;

    @Autowired
    private ForgotPasswordRedisRepository forgotPasswordRedisRepository;

    @RequestMapping(value = "/admin/authentication/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest userRequest) {
        signUpValidationService.validate(userRequest);
        UserResponse output = userService.createUser(userRequest);
        return ResponseEntityBuilder.getBuilder().setMessage("Create User Successfully").setDetails(output).build();
    }

    @RequestMapping(value = "/authentication/forgot-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotPasswordDto email) throws MessagingException {
        userService.forgotPassword(email);
        return ResponseEntityBuilder.getBuilder().setMessage("Email has been sent already !").build();
    }


    @ResponseBody
    @RequestMapping(value = "/authentication/reset-password", method = RequestMethod.GET)
    public ResponseEntity<?> resetPassword(@ModelAttribute EmailDto emailModel) throws Throwable {
        ForgotPassWordRedisDto forgotPassWordRedisModel = forgotPasswordRedisRepository.findByEmail(emailModel.getEmail()).orElseThrow(() -> {
            throw new AppException(ErrorCode.ACTIVED_ACCOUNT);
        });
        userService.resetPassword(emailModel);
        forgotPasswordRedisRepository.deleteById(forgotPassWordRedisModel.getId());
        return ResponseEntityBuilder.getBuilder().setMessage("Your password has been reset and sent to your email").build();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        if (userService.changePassword(changePasswordRequest)) {
            return ResponseEntityBuilder.getBuilder().setMessage("Your password had been changed successfully").build();
        } else {
            return ResponseEntityBuilder.getBuilder().setMessage("Could not change your passoword").build();
        }
    }

}
