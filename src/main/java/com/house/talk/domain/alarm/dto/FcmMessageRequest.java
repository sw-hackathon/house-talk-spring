package com.house.talk.domain.alarm.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmMessageRequest {
    private String token;
    private String title;
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

        return fcmMessage.toString();
    }
}
