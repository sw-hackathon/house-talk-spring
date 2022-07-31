package com.house.talk.domain.community.dto;

import com.house.talk.domain.community.domain.Post;
import com.house.talk.domain.house.domain.Home;
import com.house.talk.domain.house.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {
    @NotNull(message = "사용자를 입력해주세요")
    private Long userId;

    @NotNull(message = "집 정보를 입력해주세요")
    private Long homeId;

    @NotNull(message = "내용을 입력해주세요")
    private String content;

    private List<MultipartFile> imgs;

    public Post toPostEntity() {
        return Post.builder()
                .home(Home.from(this.homeId))
                .user(User.from(this.userId))
                .content(this.content)
                .build();
    }

    public static String toEachFileName(MultipartFile file, int idx) {
        return new Timestamp(System.currentTimeMillis()).getTime() + String.valueOf(idx) + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
