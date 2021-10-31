package com.kingfood.backend.validation;

import com.kingfood.backend.dbprovider.UserRepository;
import com.kingfood.backend.domains.request.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultSignUpValidationService implements SignUpValidationService {

    private final UserRepository userRepository;

    @Override
    public ValidationResult validate(UserRequest userRequest) {
        return new CommandConstraintsValidationStep()
                .linkWith(new UsernameDuplicationValidationStep(userRepository))
                .linkWith(new EmailDuplicationValidationStep(userRepository))
                .verify(userRequest);
    }

    private static class CommandConstraintsValidationStep extends ValidationStep<UserRequest> {

        @Override
        public ValidationResult verify(UserRequest userRequest) {
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                final Validator validator = validatorFactory.getValidator();
                final Set<ConstraintViolation<UserRequest>> constraintsViolations = validator.validate(userRequest);
                if (!constraintsViolations.isEmpty()) {
                    return ValidationResult.invalid(constraintsViolations.iterator().next().getMessage());
                }
            }
            return checkNext(userRequest);
        }
    }

    @AllArgsConstructor
    private static class UsernameDuplicationValidationStep extends ValidationStep<UserRequest> {

        private final UserRepository userRepository;

        @Override
        public ValidationResult verify(UserRequest userRequest) {
            if (userRepository.findByUserName(userRequest.getUserName())) {
                return ValidationResult.invalid(String.format("Username [%s] is already taken", userRequest.getUserName()));
            }
            return checkNext(userRequest);
        }
    }

    @AllArgsConstructor
    private static class EmailDuplicationValidationStep extends ValidationStep<UserRequest> {

        private final UserRepository userRepository;

        @Override
        public ValidationResult verify(UserRequest command) {
            if (userRepository.findByEmail(command.getEmail())) {
                return ValidationResult.invalid(String.format("Email [%s] is already taken", command.getEmail()));
            }
            return checkNext(command);
        }
    }
}