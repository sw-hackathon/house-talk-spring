package com.house.talk.domain.community.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostResponse {
    private List<PostByHomeResponse> items;

    public static PostResponse from(List<PostByHomeResponse> postByHomeResponses) {
        return PostResponse.builder()
                .items(postByHomeResponses)
                .build();
    }
}
