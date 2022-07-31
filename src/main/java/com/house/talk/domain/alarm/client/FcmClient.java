package com.house.talk.domain.alarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "fcm", url = "https://fcm.googleapis.com")
public interface FcmClient {
    @PostMapping(value = "/v1/projects/sw-hackathon-aa3e2/messages:send")
    ResponseEntity<?> requestFcmMessage(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @RequestBody String message
    );
}
