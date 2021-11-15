package com.kingfood.backend.user;


import com.kingfood.backend.domains.dto.EmailDto;
import com.kingfood.backend.domains.dto.ForgotPasswordDto;
import com.kingfood.backend.domains.request.ChangePasswordRequest;
import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    void forgotPassword(ForgotPasswordDto forgotPasswordDto);
    void resetPassword(EmailDto emailModel);
    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    UserResponse findUserById();
}
