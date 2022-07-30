package com.house.talk.domain.alarm.application;

import com.google.auth.oauth2.GoogleCredentials;
import com.house.talk.domain.alarm.client.FcmClient;
import com.house.talk.domain.alarm.dao.FcmTokenRepository;
import com.house.talk.domain.alarm.dto.FcmMessageRequest;
import com.house.talk.domain.alarm.dto.FcmTokenRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class FcmService {

    @Value("${fcm.service.key}")
    private String FCM_KEY;

    @Value("${fcm.service.url}")
    private String FCM_URL;

    @Value("${fcm.service.scope}")
    private String FCM_SCOPE;

    private final FcmTokenRepository fcmTokenRepository;
    private final FcmClient fcmClient;

    public void sendMessage(FcmMessageRequest fcmMessageRequest) {
        String message = fcmMessageRequest.toFcmMessageAsString();

        fcmClient.requestFcmMessage(message);
    }

    private String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(
                        new FileInputStream(FCM_KEY)
                )
                .createScoped(
                        Collections.singletonList(FCM_SCOPE)
                );

        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();
    }

    public void registerToken(FcmTokenRegisterRequest fcmTokenRegisterRequest) {
        fcmTokenRepository.save(fcmTokenRegisterRequest.toEntity());
    }
}
