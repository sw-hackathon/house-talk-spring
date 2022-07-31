package com.house.talk.domain.alarm;

import com.house.talk.domain.alarm.application.FcmService;
import com.house.talk.domain.alarm.dao.FcmTokenRepository;
import com.house.talk.domain.alarm.domain.FcmToken;
import com.house.talk.domain.alarm.dto.FcmTokenRegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FcmServiceTest {

    @InjectMocks
    private FcmService fcmService;

    @Mock
    private FcmTokenRepository fcmTokenRepository;
    
    @Test
    @DisplayName("Fcm Token 저장 테스트")
    public void testRegisterToken() {
        final FcmTokenRegisterRequest fcmTokenRegisterRequest = new FcmTokenRegisterRequest(1L, "IIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDORa7Ptk2Wyu6r");
        given(fcmTokenRepository.save(any())).willReturn(fcmTokenRegisterRequest.toEntity());

        final FcmToken savedFcmToken = fcmService.registerToken(fcmTokenRegisterRequest);

        verify(fcmTokenRepository, times(1)).save(any(FcmToken.class));
        assertEquals(fcmTokenRegisterRequest.toEntity(), savedFcmToken);
    }
}
