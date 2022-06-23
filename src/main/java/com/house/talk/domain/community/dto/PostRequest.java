package com.house.talk.domain.community.dto;

import com.house.talk.domain.community.domain.Post;
import com.house.talk.domain.community.domain.PostImage;
import com.house.talk.domain.house.domain.Home;
import com.house.talk.domain.house.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class PostRequest {

    private Long userId;
    private Long homeId;
    private String content;
    private MultipartFile img1;
    private MultipartFile img2;
    private MultipartFile img3;
    private MultipartFile img4;
    private MultipartFile img5;

    public Post toPostEntity() {
        return Post.builder()
                .home(new Home(this.homeId))
                .user(new User(this.userId))
                .content(this.content)
                .build();
    }
}
