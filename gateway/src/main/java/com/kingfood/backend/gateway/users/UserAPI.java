package com.kingfood.backend.gateway.users;



import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.UserResponse;
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

    @RequestMapping(value = "/admin/authentication/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        signUpValidationService.validate(userRequest);
        UserResponse output = userService.createUser(userRequest);
        return ResponseEntityBuilder.getBuilder().setMessage("Create User Successfully").setDetails(output).build();
    }

//    @RequestMapping(value = "/authentication/forgot-password", method = RequestMethod.POST)
//    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDto email) throws MessagingException {
//        userService.forgotPassword(email);
//        return ResponseEntityBuilder.getBuilder().setMessage("Email has been sent already !").build();
//    }
//
//    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
//    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
//        if (userService.changePassword(changePasswordRequest)) {
//            return ResponseEntityBuilder.getBuilder().setMessage("Your password had been changed successfully").build();
//        } else {
//            return ResponseEntityBuilder.getBuilder().setMessage("Could not change your passoword").build();
//        }
//    }
//
//    @RequestMapping(value = "/user/update-avatar", method = RequestMethod.PUT)
//    public ResponseEntity<?> uploadImg(@RequestPart(name = "files", required = true) MultipartFile file) {
//        if (userService.changeInfo(file)) {
//            return ResponseEntityBuilder.getBuilder().setMessage("Change avatar successfully").build();
//        }else {
//            return ResponseEntityBuilder.getBuilder().setMessage("Could not change your avatar").build();
//        }
//    }

}
