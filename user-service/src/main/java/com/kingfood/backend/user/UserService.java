package com.kingfood.backend.user;


import com.kingfood.backend.domains.request.UserRequest;
import com.kingfood.backend.domains.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}
