package com.house.talk.global.error.exception;

import com.house.talk.global.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());

        this.errorCode = errorCode;
    }
}
