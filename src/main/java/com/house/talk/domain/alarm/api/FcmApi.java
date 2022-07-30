package com.house.talk.domain.alarm.api;

import com.house.talk.domain.alarm.application.FcmService;
import com.house.talk.domain.alarm.dto.FcmMessageRequest;
import com.house.talk.domain.alarm.dto.FcmTokenRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FcmApi {

    private final FcmService fcmService;

    @PostMapping("/alarm")
    public ResponseEntity<?> sendFcmMessage(@RequestBody FcmMessageRequest request) {
        fcmService.sendMessage(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<?> registerFcmToken(@RequestBody FcmTokenRegisterRequest request) {
        fcmService.registerToken(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
