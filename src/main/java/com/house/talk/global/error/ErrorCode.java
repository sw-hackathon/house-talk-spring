package com.house.talk.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER("Invalid Request Data"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String message;
}
