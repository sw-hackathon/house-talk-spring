package com.house.talk.domain.alarm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.talk.domain.alarm.dto.FcmTokenRegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FcmIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Fcm Token 저장 테스트")
    public void testRegisterToken() throws Exception {
        final FcmTokenRegisterRequest fcmTokenRegisterRequest = new FcmTokenRegisterRequest(1L, "IIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDORa7Ptk2Wyu6r");

        final ResultActions resultActions = mvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fcmTokenRegisterRequest)))
                .andDo(print());

        resultActions
                .andExpect(status().isCreated());
    }
}
