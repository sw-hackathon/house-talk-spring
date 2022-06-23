package com.house.talk.domain.community.dto;

import com.house.talk.domain.community.domain.Post;
import com.house.talk.domain.community.domain.PostImage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostDetailResponse {
    private Long postId;
    private String content;
    private List<String> imgs;
    private List<Comment> comments;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Comment {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
    }

    public static PostDetailResponse of(Post post) {
        return PostDetailResponse.builder()
                .postId(post.getId())
                .content(post.getContent())
                .imgs(
                        post.getImages().stream()
                                .map(PostImage::getImg)
                                .collect(Collectors.toList())
                )
                .comments(
                        post.getComments().stream()
                                .map(postComment -> Comment.builder()
                                        .id(postComment.getId())
                                        .content(postComment.getContent())
                                        .createdAt(postComment.getCreatedAt())
                                        .build()
                                ).collect(Collectors.toList())
                )
                .build();
    }
}
