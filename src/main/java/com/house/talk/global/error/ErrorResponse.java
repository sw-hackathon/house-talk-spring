package com.house.talk.global.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    //@Valid의 Parameter 검증을 통과하지 못한 필드가 담긴다.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private List<CustomFieldError> customFieldErrors;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class CustomFieldError {
        private String field;
        private Object value;
        private String reason;
    }

    public void setCustomFieldErrors(List<FieldError> fieldErrors) {
        this.customFieldErrors = new ArrayList<>();

        fieldErrors.forEach(error -> customFieldErrors.add(
                CustomFieldError.builder()
                        .field(error.getCodes()[0])
                        .value(error.getRejectedValue())
                        .reason(error.getDefaultMessage())
                        .build()
        ));
    }

    @Builder
    public ErrorResponse(String messasge) {
        this.message = messasge;
    }

    public static ErrorResponse fromBindingException(BindingResult bindingResult) {
        return ErrorResponse.builder()
                .messasge(
                        bindingResult.hasErrors() ?
                                bindingResult.getFieldError().getDefaultMessage() :
                                ""
                        )
                .build();
    }
}
