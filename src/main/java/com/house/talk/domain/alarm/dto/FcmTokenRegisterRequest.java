package com.house.talk.domain.alarm.dto;

import com.house.talk.domain.alarm.domain.FcmToken;
import com.house.talk.domain.house.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FcmTokenRegisterRequest {
    @NotNull(message = "사용자를 입력해주세요")
    private Long userId;

    @NotEmpty(message = "토큰을 입력해주세요")
    private String token;

    public FcmToken toEntity() {
        return FcmToken.builder()
                .token(token)
                .userId(new User(userId))
                .build();
    }
}
