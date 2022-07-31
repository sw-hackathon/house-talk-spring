package com.house.talk.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(400, "Invalid Request Data");

    private final int status;
    private final String message;
}
