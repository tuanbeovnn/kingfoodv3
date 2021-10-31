package com.kingfood.backend.validation;

import com.kingfood.backend.domains.request.UserRequest;

public interface SignUpValidationService {
    ValidationResult validate(UserRequest userRequest);
}