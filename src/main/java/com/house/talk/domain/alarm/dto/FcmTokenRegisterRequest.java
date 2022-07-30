package com.house.talk.domain.alarm.dto;

import com.house.talk.domain.alarm.domain.FcmToken;
import com.house.talk.domain.house.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmTokenRegisterRequest {
    private Long userId;
    private String token;

    public FcmToken toEntity() {
        return FcmToken.builder()
                .token(token)
                .userId(new User(userId))
                .build();
    }
}
