package com.house.talk.domain.alarm.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FcmMessage {
    private boolean validate_only;
    private Message message;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }

    public String toString() {
        String fcmMessageAsString;

        try {
            fcmMessageAsString = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            fcmMessageAsString = "";
        }

        return fcmMessageAsString;
    }
}
