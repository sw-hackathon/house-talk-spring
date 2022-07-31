package com.house.talk.domain.alarm.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FcmMessageRequest {
    @NotEmpty(message = "토큰을 입력해주세요")
    private String token;

    @NotEmpty(message = "제목을 입력해주세요")
    private String title;

    @NotEmpty(message = "문구를 입력해주세요")
    private String body;

    public String toFcmMessageAsString() {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(
                        FcmMessage.Message.builder()
                                .token(this.token)
                                .notification(FcmMessage.Notification.builder()
                                        .title(this.title)
                                        .body(this.body)
                                        .image(null)
                                        .build())
                                .build()
                )
                .validate_only(false)
                .build();

        return fcmMessage.asString();
    }
}
