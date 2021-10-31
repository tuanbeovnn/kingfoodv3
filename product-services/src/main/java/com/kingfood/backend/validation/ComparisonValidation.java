package com.kingfood.backend.validation;

import com.kingfood.backend.domains.request.UserRequest;

public interface ComparisonValidation {
    boolean validate (UserRequest userRequest);
}
