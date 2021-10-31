package com.kingfood.backend.domains.request;

import com.kingfood.backend.domains.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.kingfood.backend.domains.request.CategoryRequest.ValidationResult.invalid;
import static com.kingfood.backend.domains.request.CategoryRequest.ValidationResult.valid;

@Getter
@Setter
public class CategoryRequest {
    private String name;
    private String code;

    CategoryRequest(String name, String code){
        this.name = name;
        this.code = code;
    }


    public interface CategoryValidation extends Function<CategoryRequest, ValidationResult> {
        static CategoryValidation nameIsNotEmpty() {
            return holds(cate -> !cate.name.trim().isEmpty(), "Name is empty.");
        }

        static CategoryValidation codeIsNotEmpty() {
            return holds(cate -> !cate.code.trim().isEmpty(), "Code is empty.");
        }

        static CategoryValidation holds(Predicate<CategoryRequest> p, String message){
            return cate -> p.test(cate) ? valid() : invalid(message);
        }

        default CategoryValidation and(CategoryValidation other) {
            return cate -> {
                final ValidationResult result = this.apply(cate);
                return result.isValid() ? other.apply(cate) : result;
            };
        }
    }

    public interface WebValidation {
        static CategoryValidation all(CategoryValidation... validations){
            return cate -> {
                String reasons = Arrays.stream(validations)
                        .map(v -> v.apply(cate))
                        .filter(r -> !r.isValid())
                        .map(r -> r.getReason().get())
                        .collect(Collectors.joining("\n"));
                return reasons.isEmpty()?valid():invalid(reasons);
            };
        }
    }


    interface ValidationResult {
        static ValidationResult valid() {
            return ValidationSupport.valid();
        }

        static ValidationResult invalid(String reason) {
            return new Invalid(reason);
        }

        boolean isValid();

        Optional<String> getReason();
    }

    private final static class Invalid implements ValidationResult {

        private final String reason;

        Invalid(String reason) {
            this.reason = reason;
        }

        public boolean isValid() {
            return false;
        }

        public Optional<String> getReason() {
            return Optional.of(reason);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Invalid invalid = (Invalid) o;
            return Objects.equals(reason, invalid.reason);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reason);
        }

        @Override
        public String toString() {
            return "Invalid[" +
                    "reason='" + reason + '\'' +
                    ']';
        }
    }

    private static final class ValidationSupport {
        private static final ValidationResult valid = new ValidationResult() {
            public boolean isValid() {
                return true;
            }

            public Optional<String> getReason() {
                return Optional.empty();
            }
        };

        static ValidationResult valid() {
            return valid;
        }
    }
}
