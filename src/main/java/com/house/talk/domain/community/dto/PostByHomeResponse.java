package com.house.talk.domain.community.dto;

import com.house.talk.domain.community.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostByHomeResponse {
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
    private int commentCnt;

    public static PostByHomeResponse from(Post post) {
        return PostByHomeResponse.builder()
                .postId(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .commentCnt(post.getComments().size())
                .build();
    }
}
