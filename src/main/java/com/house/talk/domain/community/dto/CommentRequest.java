package com.house.talk.domain.community.dto;

import com.house.talk.domain.community.domain.Post;
import com.house.talk.domain.community.domain.PostComment;
import com.house.talk.domain.house.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {
    private Long userId;
    private Long postId;
    private String content;

    public PostComment toEntity() {
        return PostComment.builder()
                .post(new Post(this.postId))
                .user(new User(this.userId))
                .content(this.content)
                .build();
    }
}
